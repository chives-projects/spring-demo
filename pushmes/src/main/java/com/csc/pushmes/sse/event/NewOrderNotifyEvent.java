package com.csc.pushmes.sse.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

/**
 * @Description:
 * @PackageName: com.csc.pushmes.event
 * @Author: csc
 * @Create: 2022-09-28 16:59
 * @Version: 1.0
 */
public class NewOrderNotifyEvent extends ApplicationEvent {
    private String messageBody;
    private SseEmitter sseEmitter;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    private NewOrderNotifyEvent(Object source) {
        super(source);
    }

    public NewOrderNotifyEvent(Object source, String messageBody, SseEmitter sseEmitter) {
        this(source);
        this.messageBody = messageBody;
        this.sseEmitter = sseEmitter;
    }

    public SseEmitter getSseEmitter() {
        return sseEmitter;
    }

    public String getMessageBody() {
        return messageBody;
    }
}
