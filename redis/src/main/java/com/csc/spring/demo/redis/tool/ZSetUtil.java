package com.csc.spring.demo.redis.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/27 18:15
 */
@Component
public class ZSetUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    public boolean add(String key, String value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    public Double score(String key, Object o) {
        return redisTemplate.opsForZSet().score(key, o);
    }

    /**
     * 获取zset的大小
     */
    public Long add(String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    public Double incrementScore(String key, String value, double delta) {
        return redisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    public Long remove(String key, Object... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    /**
     * 获取score在start到end之间的value
     */
    public Set range(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Set rangeByScore(String key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Set rangeByScore(String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max, offset, count);
    }

    public Long count(String key, double min, double max) {
        return redisTemplate.opsForZSet().count(key, min, max);
    }
}
