package com.csc.spring.demo.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: rabbitmq默认 Exchange的队列和交互机
 * @author: csc
 * @create: 2021/3/28 11:52
 */
@Configuration
public class DefaultExchangeConfig {
    private String hello = "hello";
    private String user = "user";
    private String distribute = "distribute";

    /**
     * 申明hello队列
     *
     * @return
     */
    @Bean
    public Queue helloQueue() {
        return new Queue(hello);
    }

    /**
     * 申明user队列
     *
     * @return
     */
    @Bean
    public Queue userQueue() {
        return new Queue(user);
    }

    /**
     * 申明distribute队列
     *
     * @return
     */
    @Bean
    public Queue DistribuQueue() {
        return new Queue(distribute);
    }
}
