package com.csc.spring.demo.rabbitmq.config;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/28 11:50
 */
@Configuration
public class RabbitConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    /**
     * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
     *
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplateNew() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        // 使用Confirm模式，关闭掉事务
        // template.setChannelTransacted(true);
        return template;
    }

    /**
     * 声明transition队列，防止直接启动报错
     *
     * @return
     */
    @Bean
    public Queue transitionQueueA() {
        return new Queue("transition");
    }


    /**
     * 声明transition2队列
     *
     * @return
     */
    @Bean
    public Queue transitionQueue() {
        return new Queue("transition2");
    }

    /**
     * Spring Boot开启事务(异步模式)
     * 使用Confirm模式时，需要注释掉
     *
     * @return
     */
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setTransactionManager(rabbitTransactionManager());
//        container.setChannelTransacted(true);
//        // 开启手动确认
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        container.setQueues(transitionQueue());
//        container.setMessageListener(new TransitionConsumer());
//        return container;
//    }


    /**
     * 事务管理
     *
     * @return
     */
    @Bean
    public RabbitTransactionManager rabbitTransactionManager() {
        return new RabbitTransactionManager(connectionFactory);
    }

    /**
     * 自定义消费者
     */
    public class TransitionConsumer implements ChannelAwareMessageListener {

        @Override
        public void onMessage(Message message, Channel channel) throws Exception {
            byte[] body = message.getBody();
            System.out.println("TransitionConsumer: " + new String(body));
            // 确认消息成功消费
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            // 除以0，模拟异常，进行事务回滚
            // int t = 1 / 0;
        }
    }
}
