package com.spring.security.demo.smscode;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @author Haotian
 * @version 1.0.0
 * @date 2020/6/29 19:47
 */
public class SmsCodeAuthenticationToken extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    /**
     * 存放认证信息，认证之前存放手机号，认证之后存放登录的用户
     */
    private final Object principal;

    public SmsCodeAuthenticationToken(Object phone) {
        super( null );
        this.principal = phone;
        setAuthenticated( false );
    }

    public SmsCodeAuthenticationToken(Object principal,
                                      Collection<? extends GrantedAuthority> authorities) {
        super( authorities );
        this.principal = principal;
        super.setAuthenticated( true ); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead" );
        }

        super.setAuthenticated( false );
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
