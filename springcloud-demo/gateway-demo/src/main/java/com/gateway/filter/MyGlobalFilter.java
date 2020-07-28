package com.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局过滤器
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/28 15:25
 **/
@Component
@Slf4j
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info( "-----------------全局过滤器MyGlobalFilter-------------------" );
        String token = exchange.getRequest().getQueryParams().getFirst( "token" );
        // 不存在token返回401未授权
        if (StringUtils.isNotBlank( token )) {
            exchange.getResponse().setStatusCode( HttpStatus.UNAUTHORIZED );
            return exchange.getResponse().setComplete();
        }
        // 存在token放行请求
        return chain.filter( exchange );
    }

    @Override
    public int getOrder() {
        // 值越小越先执行
        return 1;
    }
}