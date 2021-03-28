package com.csc.spring.demo.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/28 18:08
 */
@SpringBootTest
public class FanoutExchangeTest {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void send() {
        String msgString = "fanoutSender : hello i am anumbrella";
        System.out.println(msgString);
        rabbitTemplate.convertAndSend("fanoutExchange", "abcd.ee.ttt", msgString);
    }
}
