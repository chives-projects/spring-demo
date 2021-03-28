package com.csc.spring.demo.rabbitmq.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TransactionReceiver {

    @RabbitListener(queues = "transition")
    public void process(Message message, Channel channel) throws IOException {
        System.out.println("TransactionReceiver2  : " + new String(message.getBody()));
    }

}
