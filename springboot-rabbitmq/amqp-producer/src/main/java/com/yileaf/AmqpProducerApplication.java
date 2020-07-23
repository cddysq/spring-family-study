package com.yileaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 消息提供启动类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/23 15:21
 **/
@SpringBootApplication
public class AmqpProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run( AmqpProducerApplication.class, args );
    }

}