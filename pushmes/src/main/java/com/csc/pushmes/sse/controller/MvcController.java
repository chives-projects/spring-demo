package com.csc.pushmes.sse.controller;

import com.csc.pushmes.sse.event.NewOrderNotifyEvent;
import com.csc.pushmes.sse.event.NewOrderNotifyEventPublisherAware;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: RealTimePushMesController
 * @PackageName: com.csc.pushmes.controller
 * @Author: csc
 * @Create: 2022-06-20 17:53
 * @Version: 1.0
 */
@RestController
//@Api(tags = "mvc")
@RequestMapping("/api/mvc")
@CrossOrigin(origins = "*")
public class MvcController {
    //SseEmitter 的形式
    @GetMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter test() throws IOException {
        SseEmitter emitter = new SseEmitter();
        //自定义id,event,retry等字段
        SseEmitter.SseEventBuilder builder = SseEmitter.event()
                .name("stop")
                .id(UUID.randomUUID().toString())
                .data("hello eventsource2");
        emitter.send(builder);
        emitter.complete();
        return emitter;
    }


    //一次性事件
    private static final String[] WORDS = "The quick brown fox jumps over the lazy dog.".split(" ");
    private final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    @GetMapping(path = "/words", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    SseEmitter getWords() {
        SseEmitter emitter = new SseEmitter();

        cachedThreadPool.execute(() -> {
            try {
                for (int i = 0; i < WORDS.length; i++) {
                    emitter.send(WORDS[i]);
                    TimeUnit.SECONDS.sleep(1);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }




    //周期性
    private final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(1);
    private final AtomicInteger id = new AtomicInteger();
    private final SseEmitters emitters = new SseEmitters();

    @PostConstruct
    void init() {
        scheduledThreadPool.scheduleAtFixedRate(() -> {
            emitters.send("performanceService.getPerformance()");
        }, 0, 1, TimeUnit.SECONDS);
    }

    @GetMapping(path = "/schedule", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    SseEmitter getPerformance() {
        return emitters.add(new SseEmitter());
    }





    //事件机制
    private Map<String, SseEmitter> sseEmitters = new HashMap<>();
    @Resource
    private NewOrderNotifyEventPublisherAware newOrderNotifyEventPublisherAware;

    @GetMapping(path = "/events/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @CrossOrigin
    public SseEmitter handle(@PathVariable String id) {
        SseEmitter sseEmitter = new SseEmitter();
        this.sseEmitters.put(id, sseEmitter);
        return sseEmitter;
    }


    @GetMapping("/send/{id}")
    public void send(@PathVariable String id) {
        Map<String, String> map = new HashMap<>(1);
        map.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        String body = new String("body aaaaaaaaaaa");
        Optional.of(sseEmitters.get(id)).ifPresent(sseEmitter -> {
            NewOrderNotifyEvent newOrderNotifyEvent = new NewOrderNotifyEvent(false, body, sseEmitter);
            newOrderNotifyEventPublisherAware.publish(newOrderNotifyEvent);
        });
    }

}
