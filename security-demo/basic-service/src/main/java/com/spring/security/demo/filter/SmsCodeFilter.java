package com.spring.security.demo.filter;

import cn.hutool.core.util.StrUtil;
import com.spring.security.demo.config.MyUserDetails;
import com.spring.security.demo.constant.Constants;
import com.spring.security.demo.handler.MyAuthenticationFailureHandler;
import com.spring.security.demo.mapper.UserDetailsServiceMapper;
import com.spring.security.demo.pojo.SmsCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * 短信验证码过滤器
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/3 19:27
 */
@Component
public class SmsCodeFilter extends OncePerRequestFilter {
    @Resource
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Resource
    private UserDetailsServiceMapper userDetailsServiceMapper;

    /**
     * 登录请求路径
     */
    private static final String LOGIN_URI = "/smsLogin";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // 必须是登录的post请求才能进行验证，其他的直接放行
        if (StrUtil.equals( LOGIN_URI, request.getRequestURI() ) && StrUtil.endWithIgnoreCase( request.getMethod(), "post" )) {
            try {
                // 验证谜底与用户输入是否匹配
                validate( new ServletWebRequest( request ) );
            } catch (AuthenticationException e) {
                // 交给失败处理类进行进行处理
                myAuthenticationFailureHandler.onAuthenticationFailure( request, response, e );
                return;
            }
        }
        // 通过校验，就放行
        filterChain.doFilter( request, response );
    }

    /**
     * 校验用户输入短信验证码
     *
     * @param request 请求对象
     * @throws ServletRequestBindingException 请求异常
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getRequest().getSession();

        String phoneInRequest = request.getParameter( "phone" );
        String smsCodeInRequest = request.getParameter( "smsCode" );
        // 判空
        if (StrUtil.isBlank( phoneInRequest )) {
            throw new SessionAuthenticationException( "手机号码不能为空!" );
        }
        if (StrUtil.isBlank( smsCodeInRequest )) {
            throw new SessionAuthenticationException( "短信验证码不能为空!" );
        }

        // 获取session池中的验证码谜底
        SmsCode smsCodeInSession = (SmsCode) session.getAttribute( Constants.SMS_CODE_SESSION_KEY );
        // 判空
        if (Objects.isNull( smsCodeInSession )) {
            throw new SessionAuthenticationException( "短信验证码不存在!" );
        }

        // 验证号码是否存在数据库
        MyUserDetails userDetails = userDetailsServiceMapper.findByUserName( phoneInRequest );
        if (Objects.isNull( userDetails )) {
            throw new SessionAuthenticationException( "您输入的手机号不是系统的注册用户" );
        }

        // 验证手机号一致性
        if (!smsCodeInSession.getPhone().equals( phoneInRequest )) {
            throw new SessionAuthenticationException( "短信发送目标与该手机号不一致！" );
        }

        // 校验服务器session池中的验证码是否过期
        if (smsCodeInSession.isExpired()) {
            session.removeAttribute( Constants.SMS_CODE_SESSION_KEY );
            throw new SessionAuthenticationException( "验证码已过期" );
        }

        // 请求验证码校验
        if (!StrUtil.equals( smsCodeInSession.getCode(), smsCodeInRequest )) {
            throw new SessionAuthenticationException( "短信验证码不匹配" );
        }

        session.removeAttribute( Constants.SMS_CODE_SESSION_KEY );
    }
}