package com.spring.security.demo.config;

import com.spring.security.demo.filter.JwtAuthenticationTokenFilter;
import com.spring.security.demo.handler.MyAuthenticationFailureHandler;
import com.spring.security.demo.handler.MyAuthenticationSuccessHandler;
import com.spring.security.demo.handler.MyLogoutSuccessHandler;
import com.spring.security.demo.service.impl.MyUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * formLogin 表达登录认证
 *
 * @author Haotian
 * @version 1.0.9
 * @date 2020/4/23 19:19
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法级别安全注解
public class FormLoginConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    @Resource
    private MyUserDetailsServiceImpl myUserDetailsServiceImpl;
    @Resource
    private DataSource dataSource;
    @Resource
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 启用jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
            // 开启退出功能
            .logout()
                // 指定退出请求的默认路径
                .logoutUrl( "/signout" )
                // 指定退出成功执行的处理方法
                .logoutSuccessHandler( myLogoutSuccessHandler)
                // deleteCookies删除指定的cookie，参数为cookie的名称
                .deleteCookies( "JSESSIONID" )
                // 开启记住密码
            .and().rememberMe(  )
                // 设置from表单“记住密码”勾选框的参数名称
                .rememberMeParameter( "remember" )
                // 设置了保存在浏览器端的cookie令牌名称
                .rememberMeCookieName( "remember-me-cookie" )
                // 设置token的有效期，即多长时间内可以免除重复登录，单位是秒。不修改配置情况下默认是2周。
                .tokenValiditySeconds( 2 * 24 * 60 * 60 )
                // 设置token持久化仓库信息
                .tokenRepository( persistentTokenRepository() )
            // 禁用跨站csrf攻击防御
            .and().csrf().disable()
                .authorizeRequests()
                    // 不需要通过登录验证就可以被访问的资源路径
                    .antMatchers( "/authentication", "/refreshtoken" ).permitAll()
                    // 登录即可访问的路径
                    .antMatchers( "/index" ).authenticated()
                    // 其他路径动态授权
                    .anyRequest().access( "@rabcService.hasPermission(request,authentication)" )
            .and().sessionManagement()
                .sessionCreationPolicy( SessionCreationPolicy.STATELESS);
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

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource( dataSource );
        return jdbcTokenRepository;
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}