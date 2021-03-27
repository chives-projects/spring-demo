package com.csc.spring.demo.hello.controller;

import com.csc.spring.demo.hello.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/13 12:00
 */
@Component
@RestController
@Api(tags = "示例controller")
@RequestMapping("hello")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @ApiOperation("get方法")
    @GetMapping(value = "get")
    public String get() {
        logger.trace("这是track日志。。。");
        logger.debug("这是debug日志。。。");
        //spring 默认设置的级别是info级别，
        // 没有指定级别的情况下，会使用spring默认的root级别（显示的是info级别的信息）
        logger.info("这是info日志。。。");
        logger.warn("这是warm日志。。。");
        logger.error("这是error日志。。。");
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("异常{}", e);
        }
        return "spring boot hello get";
    }

    @PostMapping(value = "post")
    public String post() {
        return "spring boot hello post";
    }

    @GetMapping(value = "error")
    public String error() {
        throw new NullPointerException("custom null");
    }

    @GetMapping(value = "getPath/{id}")
    public String getPath(@PathVariable("id") String id) {
        return "spring boot hello get " + id;
    }

    @GetMapping(value = "getQuery")
    public String getQuery(String name, HttpServletRequest request) {
        return "spring boot hello getQuery:" + name + ",with header:" + request.getHeader("token");
    }

    @PostMapping(value = "postBody")
    public String postBody(@RequestBody User user) {
        return "spring boot hello postBody:" + user;
    }
}

