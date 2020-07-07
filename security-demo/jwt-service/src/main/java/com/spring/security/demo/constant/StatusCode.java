package com.spring.security.demo.constant;

/**
 * 状态码常量
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 21:01
 **/
public class StatusCode {
    /**
     * 成功
     */
    public static final int OK = 200;

    /**
     * 用户输入异常
     */
    public static final int INPUT_ERROR = 400;

    /**
     * 失败
     */
    public static final int ERROR = 500;
    /**
     * 用户名或密码错误
     */
    public static final int LOGIN_ERROR = 20002;
    /**
     * 权限不足
     */
    public static final int ACCESS_ERROR = 20003;
}