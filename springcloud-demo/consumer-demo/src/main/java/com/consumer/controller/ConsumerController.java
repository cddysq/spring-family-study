package com.consumer.controller;

import com.consumer.entity.Result;
import com.consumer.pojo.Girl;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: Haotian
 * @Date: 2020/1/13 20:00
 * @Description: 消费接口
 */
@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "defaultFallback")
@Slf4j
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    @HystrixCommand
    //@HystrixCommand(fallbackMethod = "findByIdsFallback")
    public Result findByIds(@PathVariable("id") Integer id) {
        if (id == 1) {
            throw new RuntimeException( "太忙了" );
        }
        //使用负载均衡Ribbon,通过服务名自动获取ip和端口
        String url = "http://user-service/user/" + id;
        return Result.builder()
                .code( 200 ).message( "查询用户数据成功" )
                .data( restTemplate.getForObject( url, Girl[].class ) ).build();

        //获取eureka中注册的user-service实例列表，未使用负载均衡写法
        /* List<ServiceInstance> serviceInstances = discoveryClient.getInstances( "user-service" );
        ServiceInstance serviceInstance = serviceInstances.get( 0 );
        //拼接新地址
        url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;*/
    }

    /**
     * 熔断的降级逻辑方法必须跟正常逻辑方法保证：相同的参数列表和返回值声明。
     *
     * @param id 用户id
     * @return 异常提示
     */
    public Result findByIdsFallback(Integer id) {
        log.error( "查询用户信息失败。id：{}", id );
        return Result.builder()
                .code( 408 ).message( "对不起，网络太拥挤了" ).build();
    }

    /**
     * 全局默认降级方法
     *
     * @return 异常提示
     */
    public Result defaultFallback() {
        return Result.builder()
                .code( 503 ).message( "对不起，网络太拥挤了" ).build();
    }


}