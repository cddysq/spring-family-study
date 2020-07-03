package com.yileaf.filepassword.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpStatus;
import com.yileaf.filepassword.config.SystemParams;
import com.yileaf.filepassword.constant.Messages;
import com.yileaf.filepassword.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 解压密码请求接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/3 21:15
 **/
@RestController
public class PasswordController {
    @Resource
    private SystemParams systemParams;

    @GetMapping("/docker")
    public Result returnDockerPassword(@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String password) {
        if (username.equalsIgnoreCase( systemParams.getDockerUsername() ) && password.equalsIgnoreCase( systemParams.getDockerPassword() )) {
            return Result.success(
                    HttpStatus.HTTP_OK,
                    "密文=" + Base64.encode( systemParams.getDockerUsername() ),
                    Messages.DOCKER_PASSWORD_OK
            );
        }
        return Result.error( HttpStatus.HTTP_BAD_REQUEST, Messages.DOCKER_PASSWORD_ERROR );
    }
}