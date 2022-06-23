package com.csc.spring.demo.hello;

import com.csc.spring.demo.hello.event.other.MyEventPublish;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventApplicationTests {
    @Autowired
    MyEventPublish publish;

    @Test
    void event() {
        publish.event();
    }

    @Test
    void eventBatch() {
        publish.eventBatch();
    }

    @Test
    void user() {
        publish.user();
    }

    @Test
    void userBatch() {
        publish.userBatch();
    }

    @Test
    void contextPushEvent() {
        publish.contextPushEvent();
    }

}
