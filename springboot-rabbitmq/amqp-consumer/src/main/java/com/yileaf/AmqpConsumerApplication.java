package com.yileaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
* @Author: Haotian
* @Date: 2020/1/20 22:38
* @Description: 消息监听启动类
**/
@SpringBootApplication
public class AmqpConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run( AmqpConsumerApplication.class, args );
    }

}