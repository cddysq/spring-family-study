package com.yileaf.config;

import com.yileaf.interceptor.MyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Haotian
 * @Date: 2020/1/12 18:54
 * @Description: 注册拦截器
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    /**
     * 将拦截器注册到spring ioc容器
     *
     * @return myInterceptor
     */
    @Bean
    public MyInterceptor myInterceptor() {
        return new MyInterceptor();
    }

    /**
     * 重写该方法；往拦截器链添加自定义拦截器
     *
     * @param registry 拦截器链
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //通过registry添加myInterceptor拦截器，并设置拦截器路径为 /*
        registry.addInterceptor( myInterceptor() ).addPathPatterns( "/*" );
    }
}
