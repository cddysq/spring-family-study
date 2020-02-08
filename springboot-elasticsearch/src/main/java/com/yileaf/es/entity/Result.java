package com.yileaf.es.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Haotian
 * @Date: 2020/2/8 20:00
 * @Description: 统一返回实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result<T> {
    private Integer code;
    private T data;
    private String message;
}