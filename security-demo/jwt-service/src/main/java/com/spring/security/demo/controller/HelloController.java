package com.spring.security.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试访问接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 18:33
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index() {
        return "hello";
    }
}
