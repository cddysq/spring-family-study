package com.spring.security.demo.service;

/**
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/7 22:23
 */
public interface JwtAuthService {
    /**
     * 用户登录认证
     *
     * @param username 用户名
     * @param password 用户密码
     * @return jwt令牌
     */
    String login(String username, String password);

    /**
     * 刷新jwt
     *
     * @param token 旧jwt
     * @return 新jwt
     */
    String refreshToken(String token);
}
