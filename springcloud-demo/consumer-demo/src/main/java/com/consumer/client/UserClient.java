package com.consumer.client;

import com.consumer.client.fallback.UserClientFallback;
import com.consumer.config.FeignConfig;
import com.consumer.pojo.Girl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: Haotian
 * @Date: 2020/1/15 14:55
 * @Description: feign客户端
 */
@FeignClient(value = "user-service", fallback = UserClientFallback.class, configuration = FeignConfig.class)
public interface UserClient {
    /**
     * 根据用户id查询用户
     *
     * @param id 用户id
     * @return result
     */
    @GetMapping("/user/{id}")
    Girl[] findByIds(@PathVariable("id") Integer id);
}