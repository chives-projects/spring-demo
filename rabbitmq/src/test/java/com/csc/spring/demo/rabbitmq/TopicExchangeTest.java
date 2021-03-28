package com.csc.spring.demo.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/28 18:12
 */
@SpringBootTest
public class TopicExchangeTest {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void send() {
        String msg1 = "I am topic.mesaage msg======";
        System.out.println("sender1 : " + msg1);
        rabbitTemplate.convertAndSend("topicExchange", "topic.message", msg1);

        String msg2 = "I am topic.mesaages msg########";
        System.out.println("sender2 : " + msg2);
        rabbitTemplate.convertAndSend("topicExchange", "topic.messages", msg2);
    }

}
