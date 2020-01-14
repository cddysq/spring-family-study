package com.consumer.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @Author: Haotian
 * @Date: 2019/12/13 20:24
 * @Description: 用户实体类
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Girl {
    private Integer id;
    private String name;
    private Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String phone;
    private String bwh;
    private String address;
    private String status;

    @Override
    public String toString() {
        return "Gril{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", phone='" + phone + '\'' +
                ", bwh='" + bwh + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}