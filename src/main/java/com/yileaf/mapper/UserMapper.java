package com.yileaf.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yileaf.model.Girl;
import org.springframework.stereotype.Component;

/**
* @Author: Haotian
* @Date: 2020/1/12 16:00
* @Description: 用户crud
**/
@Component
public interface UserMapper extends BaseMapper<Girl> {

}