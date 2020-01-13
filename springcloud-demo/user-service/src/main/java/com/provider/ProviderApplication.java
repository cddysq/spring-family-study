package com.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Haotian
 * @Date: 2020/1/13 16:08
 * @Description: 用户服务启动类
 **/
@SpringBootApplication
@MapperScan("com.provider.mapper")
public class ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run( ProviderApplication.class, args );
    }

}