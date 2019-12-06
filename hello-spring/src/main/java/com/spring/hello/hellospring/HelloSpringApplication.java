package com.spring.hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author :Haotian
 * @Parameter : @SpringBootApplication 是一个组合注解，用于快捷配置启动类,同于@Configuration+@EnableAutoConfiguration+@ComponentScan的合集
 * @Parameter : @RestController 相当于@Controller + @ResponseBody 的作用结合,将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区
 */
@SpringBootApplication
@RestController
public class HelloSpringApplication {

    public static void main(String[] args) {
        //Spring-boot启动入口
        SpringApplication.run( HelloSpringApplication.class, args );
    }

    /**
     * @return 向请求(http://localhost:8080/hello)返回 Hello spring
     * @Parameter : @RequestMapping 用于建立请求URL和处理请求方法之间的对应关系
     */
    @RequestMapping("/hello")
    public String hello() {
        //指定返回数据
        return "Hello World from the Spring";
    }
}