package com.spring.security.demo.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 动态认证权限服务
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/28 18:59
 */
@Component("rabcService")
public class MyRBACService {

    /**
     * 判断某用户是否具有该request资源的访问权限
     *
     * @param request        请求对象
     * @param authentication 认证对象
     * @return 是否具访问权限
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        // 获取认证主体
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            // 对象转换
            UserDetails userDetails = ((UserDetails) principal);
            // 将请求路径封装为权限集合
            List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList( request.getRequestURI() );
            // 判断此路径是否被包含在已知权限里
            return userDetails.getAuthorities().contains( authorityList.get( 0 ) );
        }
        return false;
    }
}