package com.spring.repository;

import com.spring.entity.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @Author: Haotian
 * @Date: 2019/12/10 20:11
 * @Description: 用户数据 crud 接口
 */
@Component
public interface UserRepository extends ReactiveCrudRepository<User, String> {
    /**
     * 根据用户姓名查询用户
     *
     * @param username 用户姓名
     * @return 用户数据
     */
    Mono<User> findByUsername(String username);

    /**
     * 根据用户姓名删除用户
     *
     * @param username 用户姓名
     * @return 1=success 0=error
     */
    Mono<Long> deleteByUsername(String username);
}