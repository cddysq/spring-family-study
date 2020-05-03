package com.spring.security.demo.filter;

import cn.hutool.core.util.StrUtil;
import com.spring.security.demo.constant.Constants;
import com.spring.security.demo.handler.MyAuthenticationFailureHandler;
import com.spring.security.demo.pojo.CaptchaImageVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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
 * 图形验证码过滤器
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/5/3 19:27
 */
@Component
public class CaptchaCodeFilter extends OncePerRequestFilter {
    @Resource
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    /**
     * 登录请求路径
     */
    private static final String LOGIN_URI = "/login";

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
     * 校验用户输入图形验证码
     *
     * @param request 请求对象
     * @throws ServletRequestBindingException 请求异常
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getRequest().getSession();
        // 获取用户登录界面输入的captchaCode
        String captchaCodeInRequest = ServletRequestUtils.getStringParameter( request.getRequest(), "captchaCode" );
        // 判空
        if (StrUtil.isBlank( captchaCodeInRequest )) {
            throw new SessionAuthenticationException( "验证码不能为空" );
        }

        // 获取session池中的验证码谜底
        CaptchaImageVO captchaImageVO = (CaptchaImageVO) session.getAttribute( Constants.CAPTCHA_SESSION_KEY );
        // 判空
        if (Objects.isNull( captchaImageVO )) {
            throw new SessionAuthenticationException( "您输入的验证码不存在" );
        }

        // 校验服务器session池中的验证码是否过期
        if (captchaImageVO.isExpired()) {
            session.removeAttribute( Constants.CAPTCHA_SESSION_KEY );
            throw new SessionAuthenticationException( "验证码已过期" );
        }

        // 请求验证码校验
        if (!StrUtil.equals( captchaImageVO.getCode(), captchaCodeInRequest )) {
            throw new SessionAuthenticationException( "验证码不匹配" );
        }
    }
}