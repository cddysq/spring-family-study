package com.spring.security.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 手机验证码信息对象
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/6 10:54
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsCode extends BaseCode {
    /**
     * 手机号
     */
    private String phone;

    public SmsCode(String code, int expireAfterSeconds, String phone) {
        super( code, expireAfterSeconds );
        this.phone = phone;
    }
}