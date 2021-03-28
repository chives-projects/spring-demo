package com.csc.spring.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/28 11:58
 */
@Configuration
public class HeadersExchangeConfig {
    @Bean
    public Queue headersQueueA() {
        return new Queue("headers.A");
    }

    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("headersExchange");
    }

    @Bean
    Binding bindingHeadersExchangeA(Queue headersQueueA, HeadersExchange headersExchange) {
        // 这里x-match有两种类型
        // all:表示所有的键值对都匹配才能接受到消息
        // any:表示只要有键值对匹配就能接受到消息
        return BindingBuilder.bind(headersQueueA).to(headersExchange).where("age").exists();
    }
}
