package com.csc.spring.demo.hello.config.interceptor;

import com.csc.spring.demo.hello.config.interceptor.AuthorityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: csc
 * @create: 2021/4/12 0:13
 */
@Configuration
public class WebInterceptorConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
//        registry.addInterceptor(LoginInterceptor()).addPathPatterns("/**");
        registry
                .addInterceptor(AuthorityInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/*/doc.html","/swagger-resources/**","/v2/**");
    }

    @Bean
    public AuthorityInterceptor AuthorityInterceptor() {
        return new AuthorityInterceptor();
    }
}
