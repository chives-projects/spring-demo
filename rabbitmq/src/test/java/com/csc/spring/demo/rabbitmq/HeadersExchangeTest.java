package com.csc.spring.demo.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Hashtable;
import java.util.Map;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/28 18:11
 */
@SpringBootTest
public class HeadersExchangeTest {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void send() {

        Map<String, Object> headers = new Hashtable<String, Object>();
        headers.put("name", "jack");
        headers.put("age", 30);
        String content = headers.toString();
        MessageProperties props = MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN).setMessageId("123").setHeader("age", "30")
                .build();
        Message message = MessageBuilder.withBody(content.getBytes()).andProperties(props).build();

        System.out.println("sender1 : " + headers.toString());
        this.rabbitTemplate.convertAndSend("headersExchange", "", message);

    }
}
