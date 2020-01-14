package com.provider.controller;

import com.provider.pojo.Girl;
import com.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Haotian
 * @Date: 2020/1/13 16:11
 * @Description: 用户接口
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public List<Girl> findByIds(@PathVariable("id") Integer[] ids){
        return this.userService.findByIds(ids);
    }
}