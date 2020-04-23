package com.spring.security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * formLogin 表达登录认证
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 19:19
 */
@Configuration
public class FormLoginConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 禁用跨站csrf攻击防御
        http.csrf().disable()
            // 开启表单登录
            .formLogin()
                // 用户未登录时，访问任何资源都转跳到该路径，即登录页面
                .loginPage( "/login.html" )
                // 登录表单form中action的地址，也就是处理认证请求的路径
                .loginProcessingUrl( "/login" )
                // 登录表单form中用户名输入框input的name名，不修改的话默认是username
                .usernameParameter("uname")
                // form中密码输入框input的name名，不修改的话默认是password
                .passwordParameter("pword")
                // 登录认证成功后默认转跳的路径
                .defaultSuccessUrl( "/index" )
            .and()
                .authorizeRequests()
                // 不需要通过登录验证就可以被访问的资源路径
                .antMatchers( "/login.html", "/login" ).permitAll()
                // 需要对外暴露的资源路径
                .antMatchers( "/mock1", "mock2" )
                    // user角色和admin角色都可以访问
                    .hasAnyAuthority( "ROLE_user", "ROLE_admin" )
                .antMatchers( "/systemlog", "/user" )
                    // admin角色可以访问
                    .hasAnyRole( "admin" )
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser( "user" )
                .password(passwordEncoder().encode( "123456" )  )
                .roles( "user" )
            .and()
                .withUser( "admin" )
                .password(passwordEncoder().encode( "123456"))
                .roles( "admin" )
            .and()
                // 配置BCrypt编码器
                .passwordEncoder( passwordEncoder() );
    }
    @Override
    public void configure(WebSecurity web) {
        // 将项目中静态资源路径开放出来
        web.ignoring().antMatchers( "/css/**", "/fonts/**", "/img/**", "/js/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}