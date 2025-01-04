package com.csc.spring.demo.config;

/**
 * @Description:
 * @Author: csc
 * @Create: 2025-01-04
 */
import org.slf4j.MDC;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.util.Map;

public class MDCContextUtil {

    // 保存当前线程的 MDC 信息到 Reactor Context 中
    public static Context captureMDCContext() {
        Map<String, String> mdcContext = MDC.getCopyOfContextMap();
        return Context.of("mdc", mdcContext);
    }

    // 从 Reactor Context 中恢复 MDC 信息
    public static void restoreMDCContext(Map<String, String> mdcContext) {
        if (mdcContext != null) {
            MDC.setContextMap(mdcContext);
        } else {
            MDC.clear();
        }
    }

    // 用于恢复 MDC 上下文的 Mono 操作符
    public static <T> Mono<T> withMDC(Mono<T> mono) {
        return mono.contextWrite(context -> {
            Map<String, String> mdcContext = context.getOrDefault("mdc", null);
            restoreMDCContext(mdcContext);
            return context;
        });
    }
}

