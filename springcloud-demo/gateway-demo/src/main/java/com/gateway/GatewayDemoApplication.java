package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author: Haotian
 * @Date: 2020/1/15 16:24
 * @Description: 网关服务启动类
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run( GatewayDemoApplication.class, args );
    }

}