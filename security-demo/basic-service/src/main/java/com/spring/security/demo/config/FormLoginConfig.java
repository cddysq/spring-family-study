package com.spring.security.demo.config;

import com.spring.security.demo.handler.MyAuthenticationFailureHandler;
import com.spring.security.demo.handler.MyAuthenticationSuccessHandler;
import com.spring.security.demo.handler.MyExpiredSessionStrategy;
import com.spring.security.demo.service.impl.MyUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * formLogin 表达登录认证
 *
 * @author Haotian
 * @version 1.0.6
 * @date 2020/4/23 19:19
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法级别安全注解
public class FormLoginConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            // 开启记住密码
        http.rememberMe(  )
                // 设置from表单“记住密码”勾选框的参数名称
                .rememberMeParameter( "remember" )
                // 设置了保存在浏览器端的cookie令牌名称
                .rememberMeCookieName( "remember-me-cookie" )
                // 设置token的有效期，即多长时间内可以免除重复登录，单位是秒。不修改配置情况下默认是2周。
                .tokenValiditySeconds( 2 * 24 * 60 * 60 )
            // 禁用跨站csrf攻击防御
            .and().csrf().disable()
            // 开启表单登录
            .formLogin()
                // 用户未登录时，访问任何资源都转跳到该路径，即登录页面
                .loginPage( "/login.html" )
                // 登录表单form中action的地址，也就是处理认证请求的路径
                .loginProcessingUrl( "/login" )
                    // 登录表单form中用户名输入框input的name名，不修改的话默认是username
                    .usernameParameter( "username" )
                    // form中密码输入框input的name名，不修改的话默认是password
                    .passwordParameter( "password" )
                .successHandler( myAuthenticationSuccessHandler )
                .failureHandler( myAuthenticationFailureHandler )
            // 登录认证成功后默认转跳的路径
            //.defaultSuccessUrl( "/index" )
            .and()
                .authorizeRequests()
                    // 不需要通过登录验证就可以被访问的资源路径
                    .antMatchers( "/login.html", "/login" ).permitAll()
                    // 登录即可访问的路径
                    .antMatchers( "/index" ).authenticated()
                    // 其他路径动态授权
                    .anyRequest().access( "@rabcService.hasPermission(request,authentication)" )
            .and()
                .sessionManagement()
                    // Session安全策略，Spring Security在需要时才创建session（默认）
                    .sessionCreationPolicy( SessionCreationPolicy.IF_REQUIRED )
                    // 非法或超时session跳转页面
                    .invalidSessionUrl( "/login.html" )
                // session会话保护，同一个cookies的SESSIONID用户，每次登录验证将创建一个新的HTTP会话，旧的HTTP会话将无效，并且旧会话的属性将被复制
                .sessionFixation().migrateSession()
                // 设置同一个用户最大的登录数量
                .maximumSessions( 1 )
                    // true表示已经登录就不予许再次登录，false表示允许再次登录但是之前的登录会下线。
                    .maxSessionsPreventsLogin( false )
                    // 设置下线处理策略
                    .expiredSessionStrategy(new MyExpiredSessionStrategy() );

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( myUserDetailsServiceImpl )
                .passwordEncoder( passwordEncoder() );
    }

    @Override
    public void configure(WebSecurity web) {
        // 将项目中静态资源路径开放出来
        web.ignoring().antMatchers( "/css/**", "/fonts/**", "/img/**", "/js/**" );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}