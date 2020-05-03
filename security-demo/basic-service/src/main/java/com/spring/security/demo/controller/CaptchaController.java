package com.spring.security.demo.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.spring.security.demo.constant.Constants;
import com.spring.security.demo.pojo.CaptchaImageVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 图形验证码接口
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/3 18:09
 */
@Controller
public class CaptchaController {
    @GetMapping("/captcha")
    public void captcha(HttpSession session, HttpServletResponse response) {
        // 设置请求头
        response.setDateHeader( "Expires", 0 );
        response.setHeader( "Cache-Control", "no-store, no-cache, must-revalidate" );
        response.addHeader( "Cache-Control", "post-check=0, pre-check=0" );
        response.setHeader( "Pragma", "no-cache" );
        response.setContentType( "image/jpeg" );

        // 使用CaptchaUtil创建验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha( 110, 40, 4, 40 );
        // 获取验证码
        String captchaCode = lineCaptcha.getCode();
        CaptchaImageVO captchaImageVO = new CaptchaImageVO( captchaCode, 2 * 60 );
        // 将验证码存到session
        session.setAttribute( Constants.CAPTCHA_SESSION_KEY, captchaImageVO );
        // 将验证码输出到浏览器
        try (ServletOutputStream outputStream = response.getOutputStream()) {
            lineCaptcha.write( outputStream );
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
    }
}