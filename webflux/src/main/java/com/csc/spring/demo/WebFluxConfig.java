package com.csc.spring.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Configuration
public class WebFluxConfig {

    @Bean
    @Scope("prototype")
    public WebClient.Builder webClientBuilder() {
        log.info("webClientBuilder create .....");
        return WebClient.builder()
                .filter(logRequest())
                .filter(logResponse());
    }

    // 请求日志拦截器
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(request -> {
            try {
                BodyInserter<?, ? super ClientHttpRequest> body = request.body();
                Field declaredField = request.body().getClass().getDeclaredField("arg$1");
                declaredField.setAccessible(true);
                Object o = declaredField.get(body);
                log.info("Request: {} {} - Headers: {} Body: {}", request.method(), request.url(), request.headers(), o);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            return Mono.just(request);

        });
    }

    // 响应日志拦截器
    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(response -> {

            // 缓存响应体并打印
            return DataBufferUtils.join(response.bodyToFlux(DataBuffer.class)) // 获取响应体内容
                    .flatMap(dataBuffer -> {
                        // 将数据缓存在 DataBuffer 中
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        DataBufferUtils.release(dataBuffer); // 释放 DataBuffer 资源

                        String body = new String(content);
                        log.info("Response: {} - Body: {}", response, body);

                        // 创建新的 Mono 来包装缓存的响应体，并返回原始的响应体，确保后续流程正常
//                        Flux<DataBuffer> cachedBody = Flux.just(response.bufferFactory().bodyFactory().wrap(content));
                        DefaultDataBufferFactory factory = new DefaultDataBufferFactory();
                        DefaultDataBuffer buffer = factory.wrap(ByteBuffer.wrap(body.getBytes(StandardCharsets.UTF_8)));
                        Flux<DataBuffer> cachedBody = Flux.just(buffer);
                        return Mono.just(response.mutate().body(cachedBody).build());
                    });

//            return Mono.just(response);
        });
    }


//    @Bean
//    public MDCWebFilter mdcWebFilter() {
//        return new MDCWebFilter();
//    }
//    @Bean
//    public HttpServer httpServer() {
//        // 创建自定义的 EventLoopGroup (线程池)
//        EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);  // 4个线程池
//        // 配置 Netty HttpServer 使用自定义的线程池
//        return HttpServer.create()
//                .runOn(eventLoopGroup)  // 使用自定义线程池
//                .doOnConnection(conn -> conn.addHandlerLast(new LoggingHandler(LogLevel.INFO)));
//    }
//
//    @Bean
//    public NettyReactiveWebServerFactory serverFactory() {
//        NettyReactiveWebServerFactory factory = new NettyReactiveWebServerFactory();
//        factory.(httpServer());
//        return factory;
//    }

//    @Bean
//    public Scheduler customScheduler() {
//        return Schedulers.newParallel("customScheduler", 8); // 创建一个大小为 8 的线程池
//    }

}
