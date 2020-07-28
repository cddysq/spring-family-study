package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置管理服务启动类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/28 15:16
 **/
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run( ConfigServerApplication.class, args );
    }
}