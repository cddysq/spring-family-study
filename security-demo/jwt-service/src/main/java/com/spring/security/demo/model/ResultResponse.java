package com.spring.security.demo.model;

import com.spring.security.demo.constant.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 请求统一返回对象
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/23 20:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultResponse<T> implements Serializable {
    private static final long serialVersionUID = -3583233934289006966L;

    /**
     * 请求状态
     */
    private Boolean flag;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    /**
     * 返回结果实体
     */
    private T data;

    public static ResultResponse<Object> success() {
        return ResultResponse.builder()
                .flag( true )
                .code( StatusCode.OK )
                .message( "success" ).build();

    }

    public static ResultResponse<Object> success(Object data) {
        return ResultResponse.builder()
                .flag( true )
                .code( StatusCode.OK )
                .message( "success" )
                .data( data ).build();
    }

    public static ResultResponse<Object> error(Integer code, String message) {
        return ResultResponse.builder()
                .flag( false )
                .code( code )
                .message( message ).build();
    }
}