package com.consumer.client.fallback;

import com.consumer.client.UserClient;
import com.consumer.pojo.Girl;
import org.springframework.stereotype.Component;

/**
 * @Author: Haotian
 * @Date: 2020/1/15 15:42
 * @Description: 用户服务hystrix降级处理
 */
@Component
public class UserClientFallback implements UserClient {

    @Override
    public Girl[] findByIds(Integer id) {
        return new Girl[]{Girl.builder().id( id ).build()};
    }
}