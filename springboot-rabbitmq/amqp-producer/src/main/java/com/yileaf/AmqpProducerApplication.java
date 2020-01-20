package com.yileaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
* @Author: Haotian
* @Date: 2020/1/20 22:12
* @Description: 消息提供启动类
**/
@SpringBootApplication
public class AmqpProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run( AmqpProducerApplication.class, args );
    }

}