package com.yileaf;

import com.yileaf.config.RabbitMqConfig;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 发送端测试
 *
 * @author Haotian
 * @version 1.0.0
 * @date 2020/7/23 15:21
 **/
@SpringBootTest
public class ProducerTest {
    // 1.注入RabbitTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        rabbitTemplate.convertAndSend( RabbitMqConfig.EXCHANGE_NAME, "boot.hello", "boot mq hello~~~" );
    }
}