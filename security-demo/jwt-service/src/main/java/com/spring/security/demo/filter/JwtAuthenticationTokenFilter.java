package com.spring.security.demo.filter;

import cn.hutool.core.util.StrUtil;
import com.spring.security.demo.utils.JwtTokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt 权限过滤器
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/7 23:14
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader( jwtTokenUtil.getHeader() );
        if (jwtToken != null && StrUtil.isNotEmpty( jwtToken )) {
            String username = jwtTokenUtil.getUsernameFromToken( jwtToken );

            // 如果可以正确的从JWT中提取用户信息，并且该用户未被授权
            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = userDetailsService.loadUserByUsername( username );

                if (jwtTokenUtil.validateToken( jwtToken, userDetails )) {
                    //给使用该JWT令牌的用户进行授权
                    UsernamePasswordAuthenticationToken authenticationToken
                            = new UsernamePasswordAuthenticationToken( userDetails, null,
                            userDetails.getAuthorities() );

                    SecurityContextHolder.getContext().setAuthentication( authenticationToken );

                }
            }
        }

        filterChain.doFilter( request, response );
    }
}
