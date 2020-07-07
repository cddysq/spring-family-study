package com.spring.security.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.spring.security.demo.constant.StatusCode;
import com.spring.security.demo.exception.UserException;
import com.spring.security.demo.model.ResultResponse;
import com.spring.security.demo.model.User;
import com.spring.security.demo.service.JwtAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户登录认证
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/7 22:18
 */
@RestController
public class JwtAuthController {
    @Resource
    private JwtAuthService jwtAuthService;

    @PostMapping("/authentication")
    public ResultResponse<Object> login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isEmpty( username ) || StrUtil.isEmpty( password )) {
            return ResultResponse.error( StatusCode.LOGIN_ERROR, "用户名密码不能为空" );
        }
        return ResultResponse.success( jwtAuthService.login( username, password ) );
    }

    @PostMapping("/refreshtoken")
    public ResultResponse<Object> refresh(@RequestHeader("${jwt.header}") String token) {
        return ResultResponse.success( jwtAuthService.refreshToken( token ) );
    }

}
