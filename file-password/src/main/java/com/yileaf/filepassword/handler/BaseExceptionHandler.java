package com.yileaf.filepassword.handler;

import com.yileaf.filepassword.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 公共异常处理
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/3 21:17
 **/
@RestControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error();
    }
}