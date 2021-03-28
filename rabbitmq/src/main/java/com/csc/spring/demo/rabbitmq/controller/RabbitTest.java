package com.csc.spring.demo.rabbitmq.controller;

import com.csc.spring.demo.rabbitmq.sender.*;
import org.springframework.beans.factory.annotation.Autowired;


public class RabbitTest {
    @Autowired
    private CallBackSender callBackSender;

    @Autowired
    private TransactionSender2 transactionSender;

    /**
     * 单生产者和单消费者
     */
    /**
     * 单生产者-多消费者
     */

    /**
     * 多生产者-多消费者
     */

    /**
     * topic exchange类型rabbitmq测试
     */
    /**
     * fanout exchange类型rabbitmq测试
     */


    /**
     * direct exchange类型rabbitmq测试
     */


    /**
     * headers exchange类型rabbitmq测试
     */


    /**
     * 实体类传输测试
     */

    /**
     * 带callback的消息发送
     */

    public void callbak() {
        callBackSender.send();
    }

    /**
     * 分发机制消息发送测试
     */

    /**
     * 事务消息发送测试
     */

    public void transition() {
        transactionSender.send("Transition:  ");
    }

}