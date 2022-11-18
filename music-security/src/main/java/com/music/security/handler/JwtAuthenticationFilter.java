package com.music.security.handler;

import com.music.modules.musicutils.CheckResult;
import com.music.dao.entity.TUser;
import com.music.modules.musicutils.JwtConstant;
import com.music.modules.musicutils.JwtUtils;
import com.music.modules.musicutils.StringUtil;
import com.music.service.service.TUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @BelongsProject: admin3
 * @BelongsPackage: com.java.admin3.security
 * @Author: 晚风
 * @CreateTime: 2022-10-17  21:03
 * @Description: TODO
 * @Version: 1.0
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final String URL_WHITELIST[] = {
            "/login",
            "/logout",
            "/captcha",
            "/password",
            "/image/**",
//            "/test/**"
    };

    @Autowired
    private TUserService tUserService;

    @Autowired
    private MyUserDetailServiceImpl myUserDetailService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader("token");
        System.out.println("请求url"+request.getRequestURI());
        // 如果token是空或者url在白名单里 则放行 让后面的springsecurity认证过滤器去认证
        if(StringUtil.isEmpty(token)|| new ArrayList<String>
                (Arrays.asList(URL_WHITELIST)).contains(request.getRequestURI())){
            chain.doFilter(request,response);
            return;
        }
        CheckResult checkResult = JwtUtils.validateJWT(token);
        if (!checkResult.isSuccess()){
            switch (checkResult.getErrCode()){
                case JwtConstant.JWT_ERRCODE_NULL:throw new JwtException("token不存在");
                case JwtConstant.JWT_ERRCODE_FAIL:throw new JwtException("token验证不通过");
                case JwtConstant.JWT_ERRCODE_EXPIRE:throw new JwtException("token过期");
            }
        }
        Claims claims = JwtUtils.parseJWT(token);
        String username = claims.getSubject();
        TUser tUser = tUserService.getByUsername(username);
        UsernamePasswordAuthenticationToken
                usernamePasswordAuthenticationToken=new
                UsernamePasswordAuthenticationToken(username,null,myUserDetailService.getUserAuthority(tUser.getId()));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request,response);

    }
}
