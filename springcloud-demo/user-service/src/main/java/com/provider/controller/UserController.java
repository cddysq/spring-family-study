package com.provider.controller;

import com.provider.pojo.Girl;
import com.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/28 15:29
 **/
@RestController
@RequestMapping("/user")
@RefreshScope //刷新配置
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public List<Girl> findByIds(@PathVariable("id") Integer[] ids) {
        return this.userService.findByIds( ids );
    }
}