package com.spring.security.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 测试方法权限的用户信息类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/28 20:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonDemo {
    /**
     * 用户名
     */
    private String name;
}