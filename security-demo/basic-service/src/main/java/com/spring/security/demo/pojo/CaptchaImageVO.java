package com.spring.security.demo.pojo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 验证码信息对象
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/3 18:15
 */
@Data
public class CaptchaImageVO {
    /**
     * 验证码文字
     */
    private String code;
    /**
     * 验证码失效时间
     */
    private LocalDateTime expireTime;

    public CaptchaImageVO(String code, int expireAfterSeconds) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds( expireAfterSeconds );
    }

    /**
     * 验证码是否失效
     *
     * @return true:验证码失效 false:验证码未失效
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter( expireTime );
    }

    public String getCode() {
        return code;
    }
}