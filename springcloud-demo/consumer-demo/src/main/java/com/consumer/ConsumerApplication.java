package com.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Haotian
 * @Date: 2020/1/13 19:53
 * @Description: 消费者启动类
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run( ConsumerApplication.class, args );
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}