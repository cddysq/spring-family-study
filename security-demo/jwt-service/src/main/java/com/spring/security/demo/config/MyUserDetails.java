package com.spring.security.demo.config;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 用户信息配置
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/28 17:25
 */
@Setter
public class MyUserDetails implements UserDetails {
    /**
     * 用户名
     */
    String username;

    /**
     * 用户密码
     */
    String password;
    /**
     * 是否没过期
     */
    boolean accountNonExpired;

    /**
     * 是否没被锁定
     */
    boolean accountNonLocked;

    /**
     * 是否没过期
     */
    boolean credentialsNonExpired;

    /**
     * 账号是否可用
     */
    Boolean enabled;

    /**
     * 用户的权限集合
     */
    Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        this.enabled = accountNonLocked;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}