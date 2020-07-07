package com.spring.security.demo.handler;

import com.alibaba.fastjson.JSON;
import com.spring.security.demo.config.MyUserDetails;
import com.spring.security.demo.constant.StatusCode;
import com.spring.security.demo.mapper.UserDetailsServiceMapper;
import com.spring.security.demo.model.ResultResponse;
import es.moki.ratelimitj.core.limiter.request.RequestLimitRule;
import es.moki.ratelimitj.core.limiter.request.RequestRateLimiter;
import es.moki.ratelimitj.inmemory.request.InMemorySlidingWindowRequestRateLimiter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private UserDetailsServiceMapper userDetailsServiceMapper;

    /**
     * 规则定义：1小时之内5次机会，就触发限流行为
     */
    Set<RequestLimitRule> rules = Collections.singleton( RequestLimitRule.of( 1 * 60, TimeUnit.MINUTES, 5 ) );
    RequestRateLimiter limiter = new InMemorySlidingWindowRequestRateLimiter( rules );

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        // 从request或request.getSession中获取登录用户名
        String username = request.getParameter( "username" );
        // 默认提示信息
        String errorMsg = "用户名或者密码输入错误,请重试 ";

        if (exception instanceof LockedException) {
            errorMsg = "您已经多次登陆失败，账户已被锁定！";
        } else {
            // 计数器加1，并判断该用户是否已经到了触发了锁定规则
            boolean reachLimit = limiter.overLimitWhenIncremented( username );
            // 如果触发了锁定规则，通过UserDetails告知Spring Security锁定账户
            if (reachLimit) {
                MyUserDetails user = (MyUserDetails) userDetailsService.loadUserByUsername( username );
                user.setAccountNonLocked( false );
                userDetailsServiceMapper.updateEnabledByUsername( user );
            }
        }
        // 异常如为图形验证异常，取出异常信息
        if (exception instanceof SessionAuthenticationException) {
            errorMsg = exception.getMessage();
        }
        if ("JSON".equals( loginType )) {
            // 如果类型为json者返回数据给前端
            response.setContentType( "application/json;charset=UTF-8" );
            response.getWriter().write( JSON.toJSONString( ResultResponse.error( StatusCode.INPUT_ERROR, errorMsg ) ) );
        } else {
            // 否者采用Spring默认机制
            super.onAuthenticationFailure( request, response, exception );
        }
    }
}