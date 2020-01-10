package com.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @Author: Haotian
 * @Date: 2020/1/9 19:58
 * @Description: 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User {
    /**
     * 注解属性id为ID
     */
    @Id
    private String id;

    /**
     * 注解属性username为索引，并且唯一
     */
    @Indexed(unique = true)
    private String username;

    /**
     * 用户电话
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 描述
     */
    private String description;

    /**
     * 用户生日
     */
    private Date birthday;
}