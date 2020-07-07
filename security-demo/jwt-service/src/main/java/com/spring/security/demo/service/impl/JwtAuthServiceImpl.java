package com.spring.security.demo.service.impl;

import com.spring.security.demo.enums.UserExceptionEnum;
import com.spring.security.demo.exception.UserException;
import com.spring.security.demo.service.JwtAuthService;
import com.spring.security.demo.utils.JwtTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/7 22:24
 */
@Service
public class JwtAuthServiceImpl implements JwtAuthService {
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public String login(String username, String password) {
        try {
            // 使用用户名密码进行登录验证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( username, password );
            Authentication authenticate = authenticationManager.authenticate( authenticationToken );
            SecurityContextHolder.getContext().setAuthentication( authenticate );
        } catch (AuthenticationException e) {
            throw new UserException( UserExceptionEnum.LOGIN_ERROR );
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername( username );
        // 生成JWT
        return jwtTokenUtil.generateToken( userDetails );
    }

    @Override
    public String refreshToken(String token) {
        if (!jwtTokenUtil.isTokenExpired( token )) {
            return jwtTokenUtil.refreshToken( token );
        }
        return null;
    }
}
