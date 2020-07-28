package com.provider.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.provider.pojo.Girl;
import org.springframework.stereotype.Component;

/**
 * 用户crud
 *
 * @author Haoti0an
 * @version 1.0.0
 * @date 2020/7/28 15:28
 **/
@Component
public interface UserMapper extends BaseMapper<Girl> {
}