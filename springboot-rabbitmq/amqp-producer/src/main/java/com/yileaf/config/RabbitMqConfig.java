package com.yileaf.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq 配置类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/23 15:22
 **/
@Configuration
public class RabbitMqConfig {
    public static final String EXCHANGE_NAME = "boot_topic_exchange";
    public static final String QUEUE_NAME = "boot_queue";

    /**
     * 1.交换机
     */
    @Bean("bootExchange")
    public Exchange bootExchange() {
        return ExchangeBuilder.topicExchange( EXCHANGE_NAME ).durable( true ).build();
    }

    /**
     * 2.Queue 队列
     */
    @Bean("bootQueue")
    public Queue bootQueue() {
        return QueueBuilder.durable( QUEUE_NAME ).build();
    }


    /**
     * 3.队列和交互机绑定关系 Binding
     * <p>
     * 1. 知道哪个队列
     * 2. 知道哪个交换机
     * 3. routing key
     */
    @Bean
    public Binding bindingQueueAndExchange(@Qualifier("bootExchange") Exchange exchange, @Qualifier("bootQueue") Queue queue) {
        return BindingBuilder.bind( queue ).to( exchange ).with( "boot.#" ).noargs();
    }

}