package com.spring.security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 页面跳转
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 18:33
 */
@Controller
public class ReturnPageController {
    /**
     * 由于Spring security 会自动拦截登录路径，并不需要群殴手动编写跳转方法
     */
    /*@GetMapping("/login")
    public String index(String username, String password) {
        return "index";
    }*/
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/systemlog")
    public String systemlog() {
        return "systemlog";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/mock1")
    public String mock1() {
        return "mock1";
    }

    @GetMapping("/mock2")
    public String mock2() {
        return "mock2";
    }
}