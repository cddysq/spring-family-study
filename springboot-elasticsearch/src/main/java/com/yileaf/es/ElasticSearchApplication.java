package com.yileaf.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/19 22:22
 **/
@SpringBootApplication
public class ElasticSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run( ElasticSearchApplication.class, args );
    }
}