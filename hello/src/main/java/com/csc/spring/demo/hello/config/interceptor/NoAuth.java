package com.csc.spring.demo.hello.config.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description: 在需要权限验证的Controller的方法上使用此注解
 * @author: csc
 * @create: 2021/4/11 23:57
 */
@Target({ElementType.METHOD, ElementType.TYPE})// 可用在方法名上
@Retention(RetentionPolicy.RUNTIME)// 运行时有效
public @interface NoAuth {
}
