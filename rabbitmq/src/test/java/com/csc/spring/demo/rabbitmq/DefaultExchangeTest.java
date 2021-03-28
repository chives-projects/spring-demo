package com.csc.spring.demo.rabbitmq;

import com.csc.spring.demo.rabbitmq.entity.User;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/28 15:23
 */
@SpringBootTest
public class DefaultExchangeTest {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Test
    public void helloSend() {
        String msg = "DefaultExchangeTest hello";
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendMsg = msg + time.format(new Date()) + " hello1 ";
        System.out.println("Sender1 : " + sendMsg);
        rabbitTemplate.convertAndSend("hello", sendMsg);
    }

    @Test
    public void userSend() {
        User user = new User();
        user.setName("csc");
        user.setAddress("China");
        System.out.println("user send : " + user.getName() + "/" + user.getAddress());
        rabbitTemplate.convertAndSend("user", user);
    }

    @Test
    public void distributeSend() {
        int i = 1;
        String message = "This is a task, and the complexity is " + i + "。" + StringUtils.repeat(".", i);
        rabbitTemplate.convertAndSend("distribute", message);
    }
}
