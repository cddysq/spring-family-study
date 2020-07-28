package com.provider.service;

import com.provider.mapper.UserMapper;
import com.provider.pojo.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 用户服务
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/28 15:27
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 更据id查询用户信息
     *
     * @param ids id数组
     * @return 用户数据list集合
     */
    public List<Girl> findByIds(Integer[] ids) {
        return this.userMapper.selectBatchIds( Arrays.asList( ids ) );
    }
}