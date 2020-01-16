package com.consumer.controller;

import com.consumer.client.UserClient;
import com.consumer.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Haotian
 * @Date: 2020/1/15 14:58
 * @Description: feign用户接口
 */
@RestController
@RequestMapping("/cf")
public class ConsumerFeignController {
    @Autowired
    private UserClient userClient;

    @GetMapping("/{id}")
    public Result findByIds(@PathVariable("id") Integer id) {
        return Result.builder()
                .code( 200 ).message( "查询用户数据成功" )
                .data( userClient.findByIds( id ) ).build();
    }

}