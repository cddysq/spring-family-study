package com.spring.security.demo.smscode;

import org.springframework.lang.Nullable;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 手机号验证码进行登录，仿造UsernamePasswordAuthenticationFilter开发
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/6/29 19:34
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "phone";

    /**
     * 请求中携带手机号的参数名称
     */
    private String phoneParameter = SPRING_SECURITY_FORM_PHONE_KEY;

    /**
     * 指定当前过滤器是否只处理POST请求
     */
    private boolean postOnly = true;

    public SmsCodeAuthenticationFilter() {
        // 指定当前过滤器处理的请求
        super( new AntPathRequestMatcher( "/smsLogin", "POST" ) );
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !"POST".equals( request.getMethod() )) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod() );
        }

        String phone = obtainPhone( request );

        if (phone == null) {
            phone = "";
        }

        phone = phone.trim();

        SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken( phone );

        setDetails( request, authRequest );

        return this.getAuthenticationManager().authenticate( authRequest );
    }

    @Nullable
    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter( phoneParameter );
    }

    protected void setDetails(HttpServletRequest request,
                              SmsCodeAuthenticationToken authRequest) {
        authRequest.setDetails( authenticationDetailsSource.buildDetails( request ) );
    }

    public void setPhoneParameter(String phoneParameter) {
        Assert.hasText( phoneParameter, "phone parameter must not be empty or null" );
        this.phoneParameter = phoneParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getPhoneParameter() {
        return phoneParameter;
    }
}
