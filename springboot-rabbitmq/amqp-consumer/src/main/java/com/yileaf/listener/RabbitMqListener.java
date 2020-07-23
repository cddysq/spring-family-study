package com.yileaf.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * mq 队列监听类
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/23 15:18
 **/
@Component
public class RabbitMqListener {

    @RabbitListener(queues = "boot_queue")
    public void listenerQueue(Message message) {
        System.out.println( new String( message.getBody() ) );
    }
}