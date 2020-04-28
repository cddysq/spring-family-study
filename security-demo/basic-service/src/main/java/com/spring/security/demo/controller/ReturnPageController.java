package com.spring.security.demo.controller;

import com.spring.security.demo.model.PersonDemo;
import com.spring.security.demo.service.DemoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

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

    private final DemoService demoService;

    public ReturnPageController(DemoService demoService) {
        this.demoService = demoService;
    }

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
        demoService.findAll();
        demoService.findOne();
        demoService.delete( Arrays.asList( 1,2 ) );
        List<PersonDemo> allPD = demoService.findAllPD();
        System.out.println(allPD);
        return "mock1";
    }

    @GetMapping("/mock2")
    public String mock2() {
        return "mock2";
    }
}