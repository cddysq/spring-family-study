package com.spring.security.demo.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 手机验证码信息对象
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/6 10:54
 */
@Data
public class SmsCode {
    /**
     * 手机号
     */
    private String phone;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证码的过期时间
     */
    private LocalDateTime expireTime;

    public SmsCode(String code, int expireAfterSeconds, String phone) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds( expireAfterSeconds );
        this.phone = phone;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter( expireTime );
    }

}