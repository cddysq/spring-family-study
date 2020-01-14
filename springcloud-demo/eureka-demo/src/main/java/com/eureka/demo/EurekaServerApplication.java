package com.eureka.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
/**
* @Author: Haotian
* @Date: 2020/1/13 21:52
* @Description: eureka服务启动类
**/
@EnableEurekaServer //声明当前应用为eureka服务
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run( EurekaServerApplication.class, args );
    }

}