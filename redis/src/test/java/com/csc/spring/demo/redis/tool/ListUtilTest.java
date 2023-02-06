package com.csc.spring.demo.redis.tool;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ListUtilTest {

    @Autowired
    private ListUtil listUtil;
    @Autowired
    private RedisTemplate redisTemplate;
    private String key = "list";

    @Test
    void leftPush() {
        redisTemplate.opsForSet().add(key, 1, 2, 3, 4, 5);
    }

    @Test
    void leftPushAll() {
    }

    @Test
    void leftPushAll1() {
    }

    @Test
    void rightPush() {
    }

    @Test
    void size() {
    }

    @Test
    void range() {
    }

    @Test
    void index() {
    }

    @Test
    void leftPop() {
    }

    @Test
    void rightPop() {
    }

    @Test
    void set() {
    }

    @Test
    void remove() {
    }
}