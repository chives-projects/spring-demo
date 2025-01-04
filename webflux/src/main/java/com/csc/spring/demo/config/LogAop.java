package com.csc.spring.demo.config;

import com.csc.spring.demo.pack.Service;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description:
 * @Author: csc
 * @Create: 2025-01-04
 */

@Slf4j
@Aspect
@Component
public class LogAop {
    /**
     * Pointcut是植入Advice的触发条件。每个Pointcut的定义包括2部分，一是表达式，二是方法签名。
     */
    @Pointcut("execution(public * com.csc.spring.demo.controller.*.*(..))")
    private void pointCut() {
    }

    @Around("pointCut()")
    public Object aroundMethod(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        long currentTimeMillis = System.currentTimeMillis();
        String functionName = point.getSignature().getName();
        String traceId = String.valueOf(UUID.randomUUID());
        MDC.put("traceId", traceId);
        log.info("traceId: {}", traceId);

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        try {
            result = point.proceed(point.getArgs());
            if (result instanceof Flux<?>) {
                List<Object> list = new ArrayList<>();
//                return flux.doOnNext(list::add)
//                        .doOnError(e -> {
//                            long costTime = System.currentTimeMillis() - currentTimeMillis;
//                            log.error("Flux方法{}执行异常", functionName, e);
//                        })
//                        .doOnComplete(
//                                () -> {
//                                    long costTime = System.currentTimeMillis() - currentTimeMillis;
//                                }
//                        );
            } else if (result instanceof Service.User) {

            }
            return result;
        } catch (Throwable e) {

            log.error("{}执行异常", functionName, e);
            throw e;
        } finally {
            MDC.clear();
        }
    }
}


