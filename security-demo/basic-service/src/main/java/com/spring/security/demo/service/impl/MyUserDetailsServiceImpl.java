package com.spring.security.demo.service.impl;

import com.spring.security.demo.config.MyUserDetails;
import com.spring.security.demo.mapper.UserDetailsServiceMapper;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户授权服务
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/28 17:40
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserDetailsServiceMapper userDetailsServiceMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //获得用户信息
        MyUserDetails userDetails = userDetailsServiceMapper.findByUserName( username );
        if (userDetails == null) {
            throw new UsernameNotFoundException( "用户名不存在" );
        }
        //获得用户角色列表
        List<String> roleList = userDetailsServiceMapper.findRoleByUserName( username );
        //通过角色列表获取权限列表
        List<String> authorities = userDetailsServiceMapper.findAuthorityByRoleCodes( roleList );

        //为角色标识加上ROLE_前缀（Spring Security规范）
        roleList = roleList.stream()
                .map( r -> "ROLE_" + r )
                .collect( Collectors.toList() );
        //角色是一种特殊的权限，所以合并
        authorities.addAll( roleList );
        //转成用逗号分隔的字符串，为用户设置权限标识
        userDetails.setAuthorities(
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join( ",", authorities )
                )
        );

        return userDetails;
    }
}