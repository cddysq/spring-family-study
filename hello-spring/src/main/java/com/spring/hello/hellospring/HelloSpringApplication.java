package com.spring.hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :Haotian
 */
@SpringBootApplication
@RestController
public class HelloSpringApplication {

    public static void main(String[] args) {
        //Spring-boot启动入口
        SpringApplication.run( HelloSpringApplication.class, args );
    }

    @RequestMapping("/hello")
    public String hello() {
        //配置访问路径，返回指定数据
        return "Hello Spring";
    }
}
