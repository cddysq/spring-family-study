package com.yileaf.web.controller;

import com.yileaf.model.Girl;
import com.yileaf.model.User;
import com.yileaf.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Haotian
 * @Date: 2020/1/12 15:37
 * @Description: 用户控制器
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    /**
     * 查询所有用户
     */
    @GetMapping("")
    public List<Girl> findAll() {
        return userService.findAll();
    }

    @PostMapping("/add")
    public String add(@RequestBody Girl girl) {
        //把数据保存到demo数据库
        userService.register( girl );
        return "success";
    }

    @GetMapping("/{id}")
    public Girl findById(@PathVariable("id") String id) {
        return userService.findById( id );
    }
}