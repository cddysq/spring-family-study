package com.spring.security.demo.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录信息
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/7 22:16
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -6916710400476487068L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;
}