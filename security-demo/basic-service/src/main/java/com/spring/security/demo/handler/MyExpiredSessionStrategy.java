package com.spring.security.demo.handler;

import com.alibaba.fastjson.JSON;
import com.spring.security.demo.constant.StatusCode;
import com.spring.security.demo.model.ResultResponse;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * session被下线(超时)之后的处理策略
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/4/27 19:50
 */
public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType( "application/json;charset=UTF-8" );
        event.getResponse().getWriter().write( JSON.toJSONString( ResultResponse.error( StatusCode.ERROR, "您的账号已在其他设备进行登录" ) ) );
    }
}