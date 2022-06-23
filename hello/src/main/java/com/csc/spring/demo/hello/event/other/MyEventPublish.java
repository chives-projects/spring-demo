package com.csc.spring.demo.hello.event.other;

import com.csc.spring.demo.hello.domain.User;
import com.csc.spring.demo.hello.event.MyApplicationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * service类帮助发布事件
 */
@Service
public class MyEventPublish {
    @Autowired
    private ApplicationEventPublisher publisher;

    public void event() {
        publisher.publishEvent(new User(1, "name"));
    }

    public void eventBatch() {
        for (int i = 0; i < 10; i++) {
            User user = new User(i, "name");
            System.out.println("--start");
            publisher.publishEvent(new MyApplicationEvent(user));
            System.out.println("---end");
        }
    }

    public void user() {
        publisher.publishEvent(new User(1, "name"));
    }

    @Async
    public void userBatch() {
        for (int i = 10; i < 20; i++) {
            User user = new User(i, "name");
            System.out.println("--start");
            publisher.publishEvent(user);
            System.out.println("---end");
        }

    }

    public void contextPushEvent() {
        MyApplicationContext.pushEvent(new MyApplicationEvent(new User(1, "name")));
    }
}
