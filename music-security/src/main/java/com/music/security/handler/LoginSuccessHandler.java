package com.music.security.handler;

import cn.hutool.json.JSONUtil;
import com.music.dao.entity.R;
import com.music.dao.entity.TUser;
import com.music.modules.musicutils.JwtUtils;
import com.music.modules.musicutils.RedisCache;
import com.music.service.service.TUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @BelongsProject: admin3
 * @BelongsPackage: com.java.admin3.security
 * @Author: 晚风
 * @CreateTime: 2022-10-17  16:13
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private TUserService sysUserService;
//    @Autowired
//    private SysRoleService sysRoleService;
//    @Autowired
//    private SysMenuService sysMenuService;
    @Autowired
    private RedisCache redisCache;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();

        String username = authentication.getName();
        String token = JwtUtils.genJwtToken(username);



//        JSONUtil.toJsonStr(R.ok("登陆成功").put("authorization",token)).getBytes();

        TUser currentUser = sysUserService.getByUsername(username);

//        //根据用户id获取所有的角色
//        List<SysRole> roleList = sysRoleService.list(new QueryWrapper<SysRole>().inSql
//                ("id","select role_id from sys_user_role where user_id=" + currentUser.getId()));
//
//        //遍历角色，获取所有菜单权限
//        Set<SysMenu> menuSet = new HashSet<>();
//        for (SysRole sysRole:roleList){
//            List<SysMenu> sysMenuList = sysMenuService.list(new QueryWrapper<SysMenu>().inSql("id","select menu_id from sys_role_menu where role_id="+ sysRole.getId()));
//            for (SysMenu sysMenu:sysMenuList){
//               menuSet.add(sysMenu);
//                }
//            }
//        currentUser.setRoles(roleList.stream().map(SysRole::getName).collect(Collectors.joining(",")));
//
//        List<SysMenu> sysMenuList = new ArrayList<>(menuSet);
//
//        //排序
//        sysMenuList.sort(Comparator.comparing(SysMenu::getOrderNum));
//
//        List<SysMenu> menuList = sysMenuService.buildTreeMenu(sysMenuList);

        //生成cookie
        String ticket = UUIDUtil.uuid();
        //将用户信息存入redis中
        redisTemplate.opsForValue().set("user:"+ ticket, currentUser);
        //
        httpServletRequest.getSession().setAttribute(ticket,username);
        CookieUtils.setCookie(httpServletRequest,httpServletResponse,"userTicket",ticket);

//        outputStream.write(JSONUtil.toJsonStr(R.ok("登陆成功").
//        put("authorization",token).
//        put("currentUser",currentUser).
//        put("menuList",menuList)).getBytes());

        outputStream.write(JSONUtil.toJsonStr(R.ok("登录成功").put("authorization",token).put("currentUser",currentUser)).getBytes(StandardCharsets.UTF_8));

        outputStream.flush();
        outputStream.close();
    }
}
