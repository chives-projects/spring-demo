package com.csc.spring.demo.hello.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/13 12:00
 */
@RestController
@RequestMapping("hello")
public class HelloController {

    @GetMapping(value = "get")
    public String get() {
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

