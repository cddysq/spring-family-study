package com.consumer.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Haotian
 * @Date: 2020/1/15 15:56
 * @Description: feign日志配置
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLogLevel() {
        //记录所有请求和响应的明细，包括头信息、请求体、元数据
        return Logger.Level.FULL;
    }
}