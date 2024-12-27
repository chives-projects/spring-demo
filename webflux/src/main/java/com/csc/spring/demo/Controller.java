package com.csc.spring.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: csc
 * @Create: 2024-12-27
 */
@RestController
@RequestMapping("/api/controller")
public class Controller {
    @Autowired
    Service service;

    @GetMapping(path = "/get", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> get(String id) {
        return service.prompt(id);
    }

    @PostMapping(path = "/post", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<String> post(@RequestBody Service.User user) {
        return service.post(user);
    }

}
