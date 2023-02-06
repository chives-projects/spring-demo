package com.csc.spring.demo.hello.config.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import java.util.Arrays;

/**
 * @description: 过滤器配置
 * @author: csc
 * @create: 2021/4/11 23:18
 */
@ServletComponentScan
@Configuration
public class WebFilterConfig {

    /**
     * 注册HTTP请求拦截器
     */
    @Bean
    public FilterRegistrationBean reqResFilter1() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        ReqResFilter1 reqResFilter1 = new ReqResFilter1();
        filterRegistrationBean.setName("reqResFilter1");//设置过滤器名称
        filterRegistrationBean.setFilter(reqResFilter1);
//        filterRegistrationBean.addUrlPatterns("*.json");//配置过滤规则
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);//执行次序

        filterRegistrationBean.addInitParameter("name","nameInitParam");//设置init参数

        return filterRegistrationBean;
    }

//    @Bean
    public FilterRegistrationBean reqResFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        ReqResFilter reqResFilter = new ReqResFilter();
        filterRegistrationBean.setFilter(reqResFilter);
        filterRegistrationBean.addUrlPatterns("*.json");//配置过滤规则
        return filterRegistrationBean;
    }
}