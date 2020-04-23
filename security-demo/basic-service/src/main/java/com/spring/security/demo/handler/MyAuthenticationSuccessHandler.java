package com.spring.security.demo.handler;

import com.alibaba.fastjson.JSON;
import com.spring.security.demo.model.ResultResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证成功处理
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 20:49
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler  {
    @Value("${spring.security.login.type}")
    private String loginType;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        if ("JSON".equals( loginType )) {
            // 如果类型为json者返回数据给前端
            response.setContentType( "application/json;charset=UTF-8" );
            response.getWriter().write( JSON.toJSONString( ResultResponse.success( "/index" ) ) );
        } else {
            // 否者采用Spring默认机制
            super.onAuthenticationSuccess( request, response, authentication );
        }
    }
}