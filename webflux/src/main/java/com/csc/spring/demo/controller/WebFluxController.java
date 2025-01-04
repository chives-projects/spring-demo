package com.csc.spring.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Description: flux 流的形式
 * SpringBootAdmin(一个监控框架) (/applications接口)使用这种方法
 * @PackageName: com.csc.pushmes.controller
 * @Author: csc
 * @Create: 2022-06-20 17:53
 * @Version: 1.0
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/webflux")
public class WebFluxController {

    @GetMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> createConnectionAndSendEvents() {
        return Flux.just("Alpha", "Omega");
    }

    private static final String[] WORDS = "The quick brown fox jumps over the lazy dog.".split(" ");

    @GetMapping(path = "/words", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<String> getWords() {
        return Flux.zip(Flux.just(WORDS), Flux.interval(Duration.ofSeconds(1))).map(Tuple2::getT1);
    }


    @GetMapping(path = "/schedule", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Object> getPerformance() {
        return Flux.interval(Duration.ofSeconds(1)).map(sequence -> "performanceService.getPerformance()");
    }


    @GetMapping(value = "customEvent/{length}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<Object>> streamtest(@PathVariable("length") int length) {

        //返回一个流 根据传入的长度n，我们每间隔一秒返回n条信息
        //事件名称默认都是message，最后添加一个stop事件
        Flux result = Flux.fromStream(IntStream.rangeClosed(1, length + 1).boxed().map(i -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            if (i > length)
                //最后增加一个事件信息 用来通知前端不要再重复请求后台了
                return ServerSentEvent.<Object>builder().id(UUID.randomUUID().toString()).event("stop") //和前端addEventListener监听的事件一一对应
                        .data(-1)//ServerSentEvent必须要传data，否在前台接受不到
                        .build();
            else return String.format("测试数据%d", i);
        }));
        return result;
    }

}
