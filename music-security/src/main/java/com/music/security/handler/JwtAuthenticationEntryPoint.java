package com.music.security.handler;

import cn.hutool.json.JSONUtil;
import com.music.dao.entity.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: admin3
 * @BelongsPackage: com.java.admin3.security
 * @Author: 晚风
 * @CreateTime: 2022-10-17  21:23
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            ServletOutputStream outputStream = httpServletResponse.getOutputStream();
            outputStream.write(JSONUtil.toJsonStr(R.error(HttpServletResponse.SC_UNAUTHORIZED,"认证失败，请登录！")).getBytes());
            outputStream.flush();
            outputStream.close();
    }
}
