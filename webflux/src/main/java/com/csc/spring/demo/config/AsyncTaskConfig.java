package com.csc.spring.demo.config;

import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description:
 * @Author: csc
 * @Create: 2025-01-04
 */
@Slf4j
@EnableAsync
@Configuration
public class AsyncTaskConfig {
    public static final String TASK_BEAN_NAME = "taskExecutor";

    @Value("${thread.pool.size:16}")
    private int corePoolSize;
    @Value("${thread.pool.max-size:64}")
    private int maxPoolSize;
    @Value("${thread.pool.queue-capacity:1000}")
    private int queueCapacity;
    @Value("${thread.pool.keep-alive:60}")
    private int keepAlive;

    /**
     * 问答主线程池
     *
     * @return Executor
     */
    @Primary
    @Bean(name = TASK_BEAN_NAME)
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = getThreadPoolTaskExecutor();
        /** 配置核心线程数 */
        taskExecutor.setCorePoolSize(corePoolSize);
        /** 配置最大线程数 */
        taskExecutor.setMaxPoolSize(maxPoolSize);
        /** 配置队列大小 */
        taskExecutor.setQueueCapacity(queueCapacity);
        /** 配置线程池中的线程的名称前缀 */
        taskExecutor.setThreadNamePrefix("assistant-task-");
        /** 允许的空闲时间*/
        taskExecutor.setKeepAliveSeconds(keepAlive);
        /**
         * rejection-policy：当pool已经达到max size的时候，如何处理新任务
         *  CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
         */
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        /** 执行初始化 */
        taskExecutor.initialize();
        return taskExecutor;
    }

    private ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor() {
            @NotNull
            @Override
            public Thread newThread(@NotNull Runnable runnable) {
                Thread thread = createThread(runnable);
                thread.setUncaughtExceptionHandler((t, ex) ->
                        log.error("异步线程内报错，线程 = {}，ex = {}", t.getName(), ex));
                return thread;
            }
        };

        // 设置tid
        threadPoolTaskExecutor.setTaskDecorator(runnable -> {
            String traceId = MDC.get("traceId");
            String apiKey = MDC.get("apiKey");
            return () -> {
                MDC.put("traceId", traceId);
                runnable.run();
                MDC.clear();
            };
        });

        return threadPoolTaskExecutor;
    }
}