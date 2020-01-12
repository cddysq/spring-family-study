package com.yileaf.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Haotian
 * @Date: 2020/1/12 10:25
 * @Description: hello 控制器
 **/
@RestController
public class HelloController {

    @GetMapping("hello/{name}")
    public String hello(@PathVariable() String name) {
        return name + "：Hello,Spring Boot";
    }
}