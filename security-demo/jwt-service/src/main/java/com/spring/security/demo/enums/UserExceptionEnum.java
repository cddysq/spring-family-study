package com.spring.security.demo.enums;

import com.spring.security.demo.constant.StatusCode;
import com.spring.security.demo.exception.ExceptionMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 用户异常
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 21:20
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserExceptionEnum implements ExceptionMessage {
    LOGIN_ERROR(false, StatusCode.LOGIN_ERROR,"用户名或密码不正确" );

    private boolean flag;
    private Integer code;
    private String message;

    @Override
    public Boolean isFlag() {
        return this.flag;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}