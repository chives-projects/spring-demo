package com.csc.spring.demo.hello.config.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description: 拦截器
 * @author: csc
 * @create: 2021/4/11 23:58
 */
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 如果不是映射到方法直接通过
        // 需要排除 ResourceHttpRequestHandler
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        NoAuth methodAnnotation = method.getAnnotation(NoAuth.class);
        // 默认所有都验证权限(因为大多数都要验证)，有 @NoAuth 注解不验证
        if (methodAnnotation == null) {
            NoAuth noAuth = ((HandlerMethod) handler).getBeanType().getAnnotation(NoAuth.class);
            if (noAuth != null) {
                System.out.println("not auth");
                return true;
            }
            // auth
            System.out.println("start auth");
            if (auth()) {
                return true;
            } else {
                throw new RuntimeException("null auth");
            }
        } else {
            // no auth
            System.out.println("not auth");
        }
        return true;
    }

    private boolean auth() {
        return true;
    }
}
