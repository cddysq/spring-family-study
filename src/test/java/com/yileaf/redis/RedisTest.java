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
        //String字符串
        redisTemplate.boundValueOps( "name" ).set( "雨宫美沙子" );
        log.info( "String name={}", redisTemplate.opsForValue().get( "name" ) );

        //Hash散列
        redisTemplate.boundHashOps( "h_key" ).put( "name", "椎名真白" );
        redisTemplate.boundHashOps( "h_key" ).put( "age", "18" );
        //获取所有域对应的值
        log.info( "hash散列所有的域：{}", redisTemplate.boundHashOps( "h_key" ).keys() );
        log.info( "hash散列所有的域值：{}", redisTemplate.boundHashOps( "h_key" ).values() );

        //List列表
        redisTemplate.boundListOps( "l_key" ).leftPush( "c" );
        redisTemplate.boundListOps( "l_key" ).leftPush( "b" );
        redisTemplate.boundListOps( "l_key" ).leftPush( "a" );
        log.info( "list列表的值：{}", redisTemplate.boundListOps( "l_key" ).range( 0, -1 ) );

        //Set集合
        redisTemplate.boundSetOps( "set_key" ).add( "a", "b", "c" );
        log.info( "set集合的所有元素：{}", redisTemplate.boundSetOps( "set_key" ).members() );

        //Sorted set有序集合
        redisTemplate.boundZSetOps( "z_key" ).add( "a", 30 );
        redisTemplate.boundZSetOps( "z_key" ).add( "b", 20 );
        redisTemplate.boundZSetOps( "z_key" ).add( "c", 10 );
        log.info( "有序set集合的所有元素：{}", redisTemplate.boundZSetOps( "z_key" ).range( 0, -1 ));
    }

}