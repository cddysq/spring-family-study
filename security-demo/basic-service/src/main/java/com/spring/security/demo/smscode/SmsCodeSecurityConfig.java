package com.spring.security.demo.smscode;

import com.spring.security.demo.filter.SmsCodeFilter;
import com.spring.security.demo.handler.MyAuthenticationFailureHandler;
import com.spring.security.demo.handler.MyAuthenticationSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 组装短信验证码登录配置文件
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/6/29 20:31
 */
@Component
public class SmsCodeSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SmsCodeFilter smsCodeFilter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
        smsCodeAuthenticationFilter.setAuthenticationManager( http.getSharedObject( AuthenticationManager.class ) );
        smsCodeAuthenticationFilter.setAuthenticationSuccessHandler( myAuthenticationSuccessHandler );
        smsCodeAuthenticationFilter.setAuthenticationFailureHandler( myAuthenticationFailureHandler );

        // 获取验证码提供者
        SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
        smsCodeAuthenticationProvider.setUserDetailsService( userDetailsService );

        // 在用户密码过滤器前面加入短信验证码校验过滤器
        http.addFilterBefore( smsCodeFilter, UsernamePasswordAuthenticationFilter.class );
        // 在用户密码过滤器后面加入短信验证码认证授权过滤器
        http.authenticationProvider( smsCodeAuthenticationProvider )
                .addFilterAfter( smsCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class );

    }
}
