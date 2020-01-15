package com.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: Haotian
 * @Date: 2020/1/15 10:43
 * @Description: 数据返回实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result implements Serializable {
    private static final long serialVersionUID = 5034060496334276574L;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;
}