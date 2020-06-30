package com.spring.security.demo.smscode;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 权限验证
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/6/29 20:07
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    /**
     * 进行身份认证的逻辑
     *
     * @param authentication 就是我们传入的Token
     * @return 认证信息
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 利用UserDetailsService获取用户信息，拿到用户信息后重新组装一个已认证的Authentication
        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername( (String) authenticationToken.getPrincipal() );
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException( "无法获取用户信息" );
        }
        SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken( userDetails, userDetails.getAuthorities() );
        authenticationResult.setDetails( authenticationToken.getDetails() );
        return authenticationResult;
    }

    /**
     * AuthenticationManager挑选一个AuthenticationProvider
     * 来处理传入进来的Token就是根据supports方法来判断的
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom( authentication );
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
