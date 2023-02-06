package com.csc.spring.demo.hello.config.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @description:
 * @author: csc
 * @create: 2021/4/13 1:02
 */
//@Order(1)
@WebFilter(urlPatterns = "/*", filterName = "reqResFilter")
public class ReqResFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("=============自定义过滤器============");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
