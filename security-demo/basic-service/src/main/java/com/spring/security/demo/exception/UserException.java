package com.spring.security.demo.exception;

import lombok.Getter;

/**
 * 用户异常
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 21:32
 **/
@Getter
public class UserException extends RuntimeException {
    private static final long serialVersionUID = 8482500389681789675L;

    private final ExceptionMessage exceptionMessage;

    public UserException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}