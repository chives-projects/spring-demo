package com.csc.spring.demo.hello.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件
 */
public class MyApplicationEvent extends ApplicationEvent {
    public MyApplicationEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }
}
