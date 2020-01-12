package com.yileaf.model;

import lombok.*;

/**
 * @Author: Haotian
 * @Date: 2020/1/12 9:56
 * @Description: 学生实体类
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Integer id;
    private String username;
    private String gender;
}