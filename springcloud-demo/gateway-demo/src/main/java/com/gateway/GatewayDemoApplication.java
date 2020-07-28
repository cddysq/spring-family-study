package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关服务启动类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/28 15:24
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run( GatewayDemoApplication.class, args );
    }

}