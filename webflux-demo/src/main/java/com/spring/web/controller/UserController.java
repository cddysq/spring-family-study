package com.spring.web.controller;

import com.spring.entity.User;
import com.spring.web.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * 用户接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/17 22:07
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 添加 or 更新用户
     *
     * @param user 用户数据
     * @return 用户信息
     */
    @PostMapping("")
    public Mono<User> save(User user) {
        return this.userService.save( user );
    }

    /**
     * 删除指定用户
     *
     * @param username 用户名
     * @return 1成功 0失败
     */
    @DeleteMapping("/{username}")
    public Mono<Long> deleteByUsername(@PathVariable String username) {
        return this.userService.deleteByUsername( username );
    }

    /**
     * 查询指定用户
     *
     * @param username 用户名
     * @return 用户信息
     */
    @GetMapping("/{username}")
    public Mono<User> findByUsername(@PathVariable("username") String username) {
        return this.userService.findByUsername( username );
    }

    /**
     * 查询所有用户
     *
     * @return 用户信息集合
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> findAll() {
        return this.userService.findAll()
                //延迟加载元素 设置时间两秒
                .delayElements( Duration.ofSeconds( 2 ) );
    }

    /**
     * 欢迎访问测试
     *
     * @return 欢迎信息
     */
    @GetMapping("/welcome")
    public Mono<String> demo() {
        return Mono.just( "Welcome to Spring WebFlux" );
    }
}