package com.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 用户服务启动类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/28 15:27
 **/
@SpringBootApplication
@MapperScan("com.provider.mapper")
@EnableDiscoveryClient  // 开启Eureka客户端发现功能
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run( ProviderApplication.class, args );
    }

}