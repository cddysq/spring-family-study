package com.spring.security.demo.handler;

import com.alibaba.fastjson.JSON;
import com.spring.security.demo.constant.StatusCode;
import com.spring.security.demo.model.ResultResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证失败处理
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 21:26
 */
@Component
public class MyAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Value("${spring.security.login.type}")
    private String loginType;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if ("JSON".equals( loginType )) {
            // 如果类型为json者返回数据给前端
            response.setContentType( "application/json;charset=UTF-8" );
            response.getWriter().write( JSON.toJSONString( ResultResponse.error( StatusCode.INPUT_ERROR, "用户名或者密码输入错误" ) ) );
        } else {
            // 否者采用Spring默认机制
            super.onAuthenticationFailure( request, response, exception );
        }
    }
}