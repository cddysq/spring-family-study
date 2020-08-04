package com.yileaf.filepassword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * 启动类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/3 21:27
 **/
@EnableOpenApi
@SpringBootApplication
public class FilePasswordApplication {

    public static void main(String[] args) {
        SpringApplication.run( FilePasswordApplication.class, args );
    }

}
