package com.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Haotian
 * @Date: 2020/1/15 18:07
 * @Description: 自定义局部过滤器，名称对应***GatewayFilterFactory
 */
@Component
@Slf4j
public class MyParamGatewayFilterFactory extends AbstractGatewayFilterFactory<MyParamGatewayFilterFactory.Config> {
    /**
     * 此处对应定义的param
     */
    public static final String PARAM_NAME = "param";

    public MyParamGatewayFilterFactory() {
        super( Config.class );
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList( PARAM_NAME );
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            //如果是配置中MyParam=对应参数，进行过滤打印内容
            if (request.getQueryParams().containsKey( config.param )) {
                request.getQueryParams().get( config.param )
                        .forEach( value ->
                                log.info( "----------局部过滤器-----过滤参数{}，过滤对应值{}", config.param, value ) );
            }
            return chain.filter( exchange );
        };
    }

    public static class Config {
        private String param;

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }
}