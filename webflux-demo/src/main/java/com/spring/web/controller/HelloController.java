package com.spring.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: Haotian
 * @Date: 2020/1/9 19:28
 * @Description: 测试接口
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just( "Welcome to reactive world ~" );
    }
}