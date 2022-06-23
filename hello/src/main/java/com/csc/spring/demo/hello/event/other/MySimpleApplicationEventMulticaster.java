package com.csc.spring.demo.hello.event.other;

import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 修改默认TaskExecutor，
 * @Author: csc
 * @Create: 2020-08-14 11:22
 * @Version: 1.0
 */
@Component(value = "applicationEventMulticaster")
public class MySimpleApplicationEventMulticaster extends SimpleApplicationEventMulticaster {

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,4,10,
            TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    public MySimpleApplicationEventMulticaster () {
        setTaskExecutor(threadPoolExecutor);
//        setTaskExecutor(Executors.newFixedThreadPool(8));
    }
}
