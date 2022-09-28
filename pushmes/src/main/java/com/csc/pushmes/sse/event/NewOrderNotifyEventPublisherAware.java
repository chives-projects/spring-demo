package com.csc.pushmes.sse.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.pushmes.event
 * @Author: csc
 * @Create: 2022-09-28 16:59
 * @Version: 1.0
 */
@Component
public class NewOrderNotifyEventPublisherAware implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    /**
     * 推送通知事件.
     *
     * @param newOrderNotifyEvent the new order notify event
     */
    public void publish(NewOrderNotifyEvent newOrderNotifyEvent) {
        applicationEventPublisher.publishEvent(newOrderNotifyEvent);
    }

}
