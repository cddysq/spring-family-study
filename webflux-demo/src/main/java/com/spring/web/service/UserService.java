package com.spring.web.service;

import com.spring.entity.User;
import com.spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author: Haotian
 * @Date: 2020/1/9 20:30
 * @Description: 用户服务
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 保存或更新。
     * 如果传入的user没有id属性，由于username是unique的，在重复的情况下有可能报错，
     * 这时找到以保存的user记录用传入的user更新它。
     */
    public Mono<User> save(User user) {
        //保存给定的实体。将返回的实例用于后续操作，因为save操作可能完全改变了实体实例。
        return userRepository.save( user )
                //onErrorResume 进行错误处理，根据用户名查询用户
                .onErrorResume( e -> userRepository.findByUsername( user.getUsername() )
                        //flatMap 将用户进行转换
                        .flatMap( orginalUser -> {
                            //设置新的id 为查询到的用户id
                            user.setId( orginalUser.getId() );
                            //进行数据更新
                            return userRepository.save( user );
                        } ) );

    }

    public Mono<Long> deleteByUsername(String username) {
        return userRepository.deleteByUsername( username );
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername( username );
    }

    /**
     * 查询所有用户
     *
     * @return 用户数据集合
     */
    public Flux<User> findAll() {
        return userRepository.findAll();
    }
}