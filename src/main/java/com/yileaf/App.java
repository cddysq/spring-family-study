package com.yileaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: Haotian
 * @Date: 2020/1/12 10:52
 * @Description: 启动类
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.yileaf.mapper") //扫描mybatis注解
public class App {
    public static void main(String[] args) {
        //启动springboot项目
        SpringApplication.run( App.class, args );
    }
}