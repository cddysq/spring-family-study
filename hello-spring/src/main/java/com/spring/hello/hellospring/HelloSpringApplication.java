package com.spring.hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Haotian
 * @Date: 2019/12/12 20:20
 * @Description: Spring-Boot first demo
 **/
@SpringBootApplication
@RestController
public class HelloSpringApplication {

    public static void main(String[] args) {
        //Spring-boot启动入口
        SpringApplication.run( HelloSpringApplication.class, args );
    }

    /**
     * @return 向请求(http://localhost:8080/hello)返回 Hello spring
     */
    @RequestMapping("/hello")
    public String hello() {
        //指定返回数据
        return "Hello World from the Spring";
    }
}