package com.yileaf.redis;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @Author: Haotian
 * @Date: 2020/1/12 20:43
 * @Description: redis 测试类
 */
@SpringBootTest
@Slf4j
public class RedisTest {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void demo() {
        //string字符串
        redisTemplate.boundValueOps( "name" ).set( "" );
        log.info( "name={}", redisTemplate.opsForValue().get( "name" ));
    }

}