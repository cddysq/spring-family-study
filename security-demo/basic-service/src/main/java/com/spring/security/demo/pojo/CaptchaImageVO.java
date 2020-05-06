package com.spring.security.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 图形验证码信息对象
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/3 18:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CaptchaImageVO extends BaseCode {

    public CaptchaImageVO(String code, int expireAfterSeconds) {
        super( code, expireAfterSeconds );
    }
}