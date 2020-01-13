package com.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.provider.pojo.Girl;
import org.springframework.stereotype.Component;

/**
 * @Author: Haotian
 * @Date: 2020/1/13 16:09
 * @Description: 用户crud
 */
@Component
public interface UserMapper extends BaseMapper<Girl> {
}