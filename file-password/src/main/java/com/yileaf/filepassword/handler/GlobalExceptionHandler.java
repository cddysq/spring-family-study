package com.yileaf.filepassword.handler;

import cn.hutool.http.HttpStatus;
import com.yileaf.filepassword.model.Result;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

/**
 * 公共异常处理
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/3 21:17
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 默认异常处理方式
     */
    @ExceptionHandler(Exception.class)
    public Result systemError(Exception e) {
        e.printStackTrace();
        return Result.error();
    }

    /**
     * 参数校验异常处理
     * 对方法上@RequestBody的Bean参数校验的处理
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return Result.error( HttpStatus.HTTP_BAD_REQUEST, Objects.requireNonNull( e.getBindingResult().getFieldError() ).getDefaultMessage() );
    }

    /**
     * 参数校验异常处理
     * 方法上单个普通类型（如：String、Long等）参数校验异常（校验注解直接写在参数前面的方式）
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e) {
        return Result.error( HttpStatus.HTTP_BAD_REQUEST, e.getConstraintViolations().iterator().next().getMessage() );
    }

}