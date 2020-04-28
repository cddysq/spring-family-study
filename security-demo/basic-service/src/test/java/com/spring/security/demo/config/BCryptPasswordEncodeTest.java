package com.spring.security.demo.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * 密码加密测试
 *
 * @author Haotian
 * @date 2020/4/28 17:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class BCryptPasswordEncodeTest {
    @Resource
    PasswordEncoder passwordEncoder;

    @Test
    void passwordEncoder() {
        System.out.println( passwordEncoder.encode( "123456" ) );
    }
}