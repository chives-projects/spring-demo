package com.csc.spring.demo.rabbitmq.receiver;

import com.csc.spring.demo.rabbitmq.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/28 18:50
 */
@Component
public class DefaultExchangeReceiver {

    @RabbitListener(queues = "hello")
    public void processReject(Message message, Channel channel) throws IOException {
        System.out.println("Receiver1  : " + new String(message.getBody()));

        // true 发送给下一个消费者
        // false 谁都不接受，从队列中删除
        // 拒绝消息，RabbitMQ把消息发送给下一个监听hello的队列(HelloReceiver2或CheckReceiver)
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(queues = "hello")
    public void processAck(Message message, Channel channel) throws IOException {
        System.out.println("CheckReceiver: " + new String(message.getBody()));
        try {
            doWork();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 使用时需要在application.properties开启手动确认设置
        // 消息的标识，false只确认当前一个消息收到，true确认所有将比第一个参数指定的 delivery tag 小的consumer都获得的消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    private static void doWork() throws InterruptedException {
        Thread.sleep(1000);
    }

    @RabbitListener(queues = "hello")
    public void process2(Message message, Channel channel) throws IOException {
        System.out.println("Receiver2 : " + new String(message.getBody()));
    }

    @RabbitListener(queues = "user")
    public void process(User user) {
        System.out.println("user receive  : " + user.getName() + " / " + user.getAddress());
    }

    /**
     * 消费者A
     *
     * @param message
     */
    @RabbitListener(queues = "distribute")
    public void processA(Message message) {
        String msg = new String(message.getBody());
        System.out.println(" DistributionReceiverA  : " + msg);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
        System.out.println(" ProccessingA... at " + time.format(new Date()));

        try {
            for (char ch : msg.toCharArray()) {
                if (ch == '.') {
                    doWork(1000);
                }
            }
        } catch (InterruptedException e) {
        } finally {
            System.out.println(" A Done! at " + time.format(new Date()));
        }
    }

    /**
     * 消费者B
     *
     * @param message
     */
    @RabbitListener(queues = "distribute")
    public void processB(Message message) {
        String msg = new String(message.getBody());
        System.out.println(" DistributionReceiverB  : " + msg);
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSSS");
        System.out.println(" ProccessingB... at " + time.format(new Date()));

        try {
            for (char ch : msg.toCharArray()) {
                if (ch == '.') {
                    doWork(1000);
                }
            }
        } catch (InterruptedException e) {
        } finally {
            System.out.println(" B Done! at " + time.format(new Date()));
        }
    }

    private void doWork(long time) throws InterruptedException {
        Thread.sleep(time);
    }

}



