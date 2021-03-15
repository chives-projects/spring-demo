package com.csc.spring.demo.redis.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/17 23:28
 */
@Component
public class SetUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    public void add(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Set members(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public boolean isMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Long size(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public void remove(String key, Object... value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    /**
     * 随机移除,并返回移除的元素
     *
     * @param key
     * @return
     */
    public Object pop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    /**
     * 将元素从一个集合移动到另一个集合
     */
    public void move(String key, Object value, String destKey) {
        redisTemplate.opsForSet().move(key, value, destKey);
    }

    /**
     * 通过集合求差值
     */
    public Set difference(String key, Collections collections) {
        return redisTemplate.opsForSet().difference(key, collections);
    }

    /**
     * 通过给定的key求2个set变量的差值
     */
    public Set difference(String key, String otherKey) {
        return redisTemplate.opsForSet().difference(key, otherKey);
    }

    /**
     * 将求出来的差值元素保存
     */
    public Long differenceAndStore(String key, String otherKey, String desKey) {
        return redisTemplate.opsForSet().differenceAndStore(key, otherKey, desKey);
    }

    /**
     * 获取2个变量中的交集
     */
    public Set intersect(String key, String otherKey) {
        return redisTemplate.opsForSet().intersect(key, otherKey);
    }

    /**
     * 获取2个变量的合集
     */
    public Set union(String key, String otherKey) {
        return redisTemplate.opsForSet().union(key, otherKey);
    }
}
