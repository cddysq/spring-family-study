package com.spring.security.demo.enums;

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
public enum UserExceptionEnum implements ExceptionMessage {
    ;

    @Override
    public Boolean isFlag() {
        return null;
    }

    @Override
    public Integer getCode() {
        return null;
    }

    @Override
    public String getMessage() {
        return null;
    }
}