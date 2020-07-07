package com.spring.security.demo.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 退出登录成功执行
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/2 19:43
 */
@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        // 处理配置无法做到的业务逻辑，如登录时长计算，清理业务相关的数据等等

        // 重定向到退出成功界面
        response.sendRedirect("/logoutSuccess.html");
    }
}