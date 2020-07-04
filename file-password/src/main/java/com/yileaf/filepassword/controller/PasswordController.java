package com.yileaf.filepassword.controller;

import cn.hutool.core.codec.Base64;
import cn.hutool.http.HttpStatus;
import com.yileaf.filepassword.config.SystemParams;
import com.yileaf.filepassword.constant.Messages;
import com.yileaf.filepassword.entity.Result;
import com.yileaf.filepassword.model.Ssm;
import com.yileaf.filepassword.service.SsmPasswordService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @Resource
    private SsmPasswordService ssmPasswordService;

    @GetMapping("/docker")
    public Result returnDockerPassword(@RequestParam(defaultValue = "") String username, @RequestParam(defaultValue = "") String password) {
        if (username.equalsIgnoreCase( systemParams.getDockerUsername() ) && password.equalsIgnoreCase( systemParams.getDockerPassword() )) {
            return Result.success(
                    "密文=" + Base64.encode( systemParams.getDockerUsername() ),
                    Messages.DOCKER_PASSWORD_OK
            );
        }
        return Result.error( HttpStatus.HTTP_OK, Messages.DOCKER_PASSWORD_ERROR );
    }

    @PostMapping("/ssm")
    public Result returnSsmPassword(@Validated @RequestBody Ssm ssm) {
        boolean flag = ssmPasswordService.getSsmPassword( ssm );
        if (flag) {
            return Result.success( "", "" );
        }
        return Result.error( HttpStatus.HTTP_OK, Messages.DOCKER_PASSWORD_ERROR );
    }
}