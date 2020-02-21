package com.yileaf.thymeleaf.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Haotian
 * @Date: 2020/2/20 22:20
 * @Description: 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String address;
}