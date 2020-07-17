package com.spring.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * 测试接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/17 22:06
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just( "Welcome to reactive world ~" );
    }
}