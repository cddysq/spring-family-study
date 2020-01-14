package com.consumer.controller;

import com.consumer.pojo.Girl;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Haotian
 * @Date: 2020/1/13 20:00
 * @Description: 消费接口
 */
@RestController
@RequestMapping("/consumer")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/{id}")
    public Girl[] findByIds(@PathVariable("id") Integer id) {
        String url = "http://localhost:9000/" + id;

        //获取eureka中注册的user-service实例列表
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances( "user-service" );
        ServiceInstance serviceInstance = serviceInstances.get( 0 );
        //拼接新地址
        url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/user/" + id;
        System.out.println("**12**");
        return restTemplate.getForObject( url, Girl[].class );
    }

}