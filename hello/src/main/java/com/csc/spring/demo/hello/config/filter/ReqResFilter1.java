package com.csc.spring.demo.hello.config.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @description: 自定义过滤器
 * @author: csc
 * @create: 2021/4/11 23:13
 */
public class ReqResFilter1 implements Filter {
    private static Logger logger = LoggerFactory.getLogger(ReqResFilter1.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取WebFilterConfig中 filterRegistrationBean.addInitParameter("name","nameInitParam")设置的参数
        System.out.println("init==============" + filterConfig.getInitParameter("name"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        // do something：auth or passing

        if (!auth()) throw new RuntimeException("null auth");

        System.out.println("dofilter==============");
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }

    private boolean auth() {
        return true;
    }
}