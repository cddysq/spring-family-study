package com.yileaf.service;

import com.yileaf.model.Girl;

import java.util.List;

/**
 * @Author：Haotian
 * @Date：2019/6/15 14:45
 */
public interface UserService {
    /**
     * 注册用户
     *
     * @param girl 用户信息
     */
    void register(Girl girl);

    /**
     * 查询所有用户
     * @return 用户信息集合
     */
    List<Girl> findAll();

    /**
     * 根据id查询用户
     * @param id 用户id
     * @return 用户信息
     */
    Girl findById(String id);
}