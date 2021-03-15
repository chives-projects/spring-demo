package com.csc.spring.demo.redis.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/17 23:28
 */
@Component
public class ListUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 将所有指定的值插入存储在键的列表的头部。如果键不存在，
     * 则在执行推送操作之前将其创建为空列表。（从左边插入）
     *
     * @param key
     * @param value
     */
    public void leftPush(String key, Object value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 批量把一个集合插入到列表中
     *
     * @param key
     * @param value
     * @return
     */
    public long leftPushAll(String key, Collection<Object> value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 批量把一个数组插入到列表中
     *
     * @param key
     * @param value
     * @return
     */
    public long leftPushAll(String key, Object... value) {
        return redisTemplate.opsForList().leftPushAll(key, value);
    }


    /**
     * 将所有指定的值插入存储在键的列表的头部。
     * 如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）
     *
     * @param key
     * @param value
     */
    public void rightPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。
     * 当key存储的值不是列表时返回错误。
     *
     * @param key
     * @return
     */
    public long size(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * 返回存储在键中的列表的指定元素。
     * 偏移开始和停止是基于零的索引，其中0是列表的第一个元素（列表的头部），1是下一个元素
     *
     * @param key
     * @param end 为-1时，表示结尾
     * @return
     */
    public List<Object> range(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 根据下标获取其值
     *
     * @param key
     * @param index
     * @return
     */
    public Object index(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 弹出最左边的元素，弹出之后该值在列表中将不复存在
     *
     * @param key
     */
    public void leftPop(String key) {
        redisTemplate.opsForList().leftPop(key);
    }

    public void rightPop(String key) {
        redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 在集合的指定位置插入元素,如果指定位置已有元素，则覆盖，没有则新增，超过集合下标+n则会报错
     */
    public void set(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 从存储在键中的列表中删除等于值的元素的第一个计数事件。
     * count> 0：删除等于从左到右移动的值的第一个元素；
     * count< 0：删除等于从右到左移动的值的第一个元素；
     * count = 0：删除等于value的所有元素。
     */
    public void remove(String key, long index, Object value) {
        redisTemplate.opsForList().remove(key, index, value);
    }
}
