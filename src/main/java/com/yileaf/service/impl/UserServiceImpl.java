package com.yileaf.service.impl;

import com.yileaf.mapper.UserMapper;
import com.yileaf.model.Girl;
import com.yileaf.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author：Haotian
 * @Date：2019/6/15 14:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(Girl girl) {
        userMapper.insert( girl );
        log.info( "当前返回主键为：{}", girl.getId() );
    }

    @Override
    public List<Girl> findAll() {
        return userMapper.selectList( null );
    }

    @Override
    public Girl findById(String id) {
       return userMapper.selectById( id );
    }
}