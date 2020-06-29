package com.spring.security.demo.controller;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.spring.security.demo.config.MyUserDetails;
import com.spring.security.demo.constant.Constants;
import com.spring.security.demo.constant.StatusCode;
import com.spring.security.demo.mapper.UserDetailsServiceMapper;
import com.spring.security.demo.model.ResultResponse;
import com.spring.security.demo.pojo.SmsCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * 短信验证码接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/6 10:48
 */
@Slf4j
@RestController
public class SmsController {
    @Resource
    private UserDetailsServiceMapper userDetailsServiceMapper;

    /**
     * 发送短信验证码
     *
     * @param phone   用户输入手机号
     * @param session 会话对象
     * @return 提示信息
     */
    @GetMapping("/sendSmsCode/{phone}")
    public ResultResponse<Object> sendSmsCode(@PathVariable("phone") String phone, HttpSession session) {
        if (StrUtil.isBlank( phone )) {
            return ResultResponse.error( StatusCode.INPUT_ERROR, "请您输入正确的手机号" );
        }
        MyUserDetails userDetails = userDetailsServiceMapper.findByUserName( phone );
        if (userDetails == null) {
            return ResultResponse.error( StatusCode.INPUT_ERROR, "您输入的手机号不是系统注册用户" );
        }
        SmsCode smsCode = new SmsCode( RandomUtil.randomNumbers( 4 ), 2 * 60, phone );
        //TODO: 2020/5/6 11:14 此处调用验证码发送服务接口
        log.info( "当前手机号：{}，mock短信验证码为：{}", phone, smsCode.getCode() );
        session.setAttribute( Constants.SMS_CODE_SESSION_KEY, smsCode );
        return ResultResponse.success( "验证码已经发送到您的手机" );
    }
}