package com.consumer.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign日志配置
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/28 15:21
 **/
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLogLevel() {
        // 记录所有请求和响应的明细，包括头信息、请求体、元数据
        return Logger.Level.FULL;
    }
}