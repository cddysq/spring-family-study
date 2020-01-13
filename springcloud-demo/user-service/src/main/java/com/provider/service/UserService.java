package com.provider.service;

import com.provider.mapper.UserMapper;
import com.provider.pojo.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Haotian
 * @Date: 2020/1/13 16:11
 * @Description: 用户服务
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<Girl> findByIds(Integer[] ids) {
        return this.userMapper.selectBatchIds( Arrays.asList( ids ) );
    }
}