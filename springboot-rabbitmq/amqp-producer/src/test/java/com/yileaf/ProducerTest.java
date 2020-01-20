package com.yileaf;

import com.yileaf.config.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Haotian
 * @Date: 2020/1/20 22:23
 * @Description: 发送端测试
 */
@SpringBootTest
public class ProducerTest {
    //1.注入RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        rabbitTemplate.convertAndSend( RabbitMQConfig.EXCHANGE_NAME, "boot.hello", "boot mq hello~~~" );
    }
}