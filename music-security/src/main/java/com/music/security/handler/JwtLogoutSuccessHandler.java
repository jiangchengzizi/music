package com.music.security.handler;

import cn.hutool.json.JSONUtil;
import com.music.dao.entity.R;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
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
 * @CreateTime: 2022-10-17  21:39
 * @Description: TODO
 * @Version: 1.0
 */
@Component
public class JwtLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream =
                httpServletResponse.getOutputStream();
        outputStream.write(JSONUtil.toJsonStr(R.ok("退出成功")).getBytes("UTF-8"));
                outputStream.flush();
        outputStream.close();
    }
}
