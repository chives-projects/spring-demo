package com.csc.pushmes.sse.event;

import org.springframework.context.ApplicationListener;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Optional;

/**
 * @Description:
 * @PackageName: com.csc.pushmes.event
 * @Author: csc
 * @Create: 2022-09-28 16:59
 * @Version: 1.0
 */
@Component
public class NewOrderNotifyListener implements ApplicationListener<NewOrderNotifyEvent> {


    @Override
    public void onApplicationEvent(NewOrderNotifyEvent event) {
        String messageBody = event.getMessageBody();
        SseEmitter.SseEventBuilder sseEvent = SseEmitter.event();
        sseEvent
//                .data(messageBody, MediaType.APPLICATION_JSON_UTF8)
                .data(messageBody)
                .id("11111")
                .name("message")
                .comment("comment");

        Optional.of(event.getSseEmitter()).ifPresent(sseEmitter -> {
            try {
                sseEmitter.send(messageBody, MediaType.APPLICATION_JSON_UTF8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
