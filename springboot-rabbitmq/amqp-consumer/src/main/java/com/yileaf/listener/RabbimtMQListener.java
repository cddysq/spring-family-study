package com.yileaf.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: Haotian
 * @Date: 2020/1/20 22:35
 * @Description: mq 队列监听类
 */
@Component
public class RabbimtMQListener {

    @RabbitListener(queues = "boot_queue")
    public void listenerQueue(Message message) {
        System.out.println( new String( message.getBody() ) );
    }
}