package com.consumer.client.fallback;

import com.consumer.client.UserClient;
import com.consumer.pojo.Girl;
import org.springframework.stereotype.Component;

/**
 * 用户服务hystrix降级处理
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/28 15:22
 **/
@Component
public class UserClientFallback implements UserClient {

    @Override
    public Girl[] findByIds(Integer id) {
        return new Girl[]{Girl.builder().id( id ).build()};
    }
}