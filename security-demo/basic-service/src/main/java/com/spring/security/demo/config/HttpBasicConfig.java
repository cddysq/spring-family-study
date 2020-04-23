package com.spring.security.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * httpBasic 认证模式
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 18:55
 */
@Configuration
public class HttpBasicConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity auth) throws Exception {
        // 开启httpBasic认证
        auth.httpBasic()
                .and()
                .authorizeRequests().anyRequest()
                //所有请求都需要登录认证才能访问
                .authenticated();
    }
}