package com.spring.security.demo.exception;

import com.spring.security.demo.constant.StatusCode;
import com.spring.security.demo.model.ResultResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 公共异常处理
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 21:37
 **/
@ControllerAdvice
@Slf4j
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultResponse<Object> error(Exception e) {
        if (e instanceof UserException) {
            //异常类型为自定义异常，抛出异常信息
            UserException userException = (UserException) e;
            ExceptionMessage message = userException.getExceptionMessage();
            return ResultResponse.builder()
                    .flag( message.isFlag() )
                    .code( message.getCode() )
                    .message( message.getMessage() ).build();
        }
        log.error( e.getMessage() );
        return ResultResponse.builder()
                .flag( false )
                .code( StatusCode.ERROR )
                .message( e.getMessage() ).build();
    }
}