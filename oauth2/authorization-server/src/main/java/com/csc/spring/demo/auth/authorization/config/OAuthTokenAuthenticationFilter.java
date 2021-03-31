package com.csc.spring.demo.auth.authorization.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: token端点过滤器
 * @author: csc
 * @create: 2021/3/31 23:20
 */
@Component
public class OAuthTokenAuthenticationFilter extends GenericFilterBean {
    private static final String OAUTH_TOKEN_URL = "/oauth2/token";

    private RequestMatcher requestMatcher;

    public OAuthTokenAuthenticationFilter() {
        //OrRequestMatcher or组合多个RequestMatcher
        this.requestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(OAUTH_TOKEN_URL, HttpMethod.POST.name())
        );
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (requestMatcher.matches((HttpServletRequest) servletRequest)) {
            // can do verify
//            servletResponse.getWriter().println("验证失败");
//            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
