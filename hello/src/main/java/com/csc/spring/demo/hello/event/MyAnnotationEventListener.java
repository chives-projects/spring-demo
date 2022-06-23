package com.csc.spring.demo.hello.event;

import com.csc.spring.demo.hello.domain.User;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 基于注解监听事件
 * 支持事件类和特殊的条件（SpEL表达式）
 */
@Component
public class MyAnnotationEventListener {
    @Async
//    @EventListener(condition = "#user.name!=null")
    @EventListener(MyApplicationEvent.class)
    public void handleEvent(MyApplicationEvent event) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        User source = (User) (event.getSource());
        System.out.println(source.getId());
    }
}
