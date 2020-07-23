package com.yileaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消息监听启动类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/23 15:20
 **/
@SpringBootApplication
public class AmqpConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run( AmqpConsumerApplication.class, args );
    }

}