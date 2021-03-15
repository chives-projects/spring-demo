package com.csc.spring.demo.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void stringRedisTemplate() {
        String strKey = "strKey";
        stringRedisTemplate.opsForValue().set(strKey, "说散就散", 20, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.opsForValue().get(strKey));
//        stringRedisTemplate.delete(strKey);
        // 重新设置过期时间为timeout
        System.out.println(stringRedisTemplate.expire(strKey, 30, TimeUnit.SECONDS));
        System.out.println(stringRedisTemplate.getExpire(strKey));
    }

    @Test
    void redisTemplate() {
        String redisKey = "redisKey";
        redisTemplate.opsForValue().set(redisKey, "redisValue", 20, TimeUnit.SECONDS);
        Object redisRes = redisTemplate.opsForValue().get(redisKey);
    }

    @Test
    void hash() {
        String hashKey = "hashKey";
        redisTemplate.opsForHash().put(hashKey, "hk", "hv");
        redisTemplate.expire(hashKey, 20, TimeUnit.SECONDS);
        Object redisRes = redisTemplate.opsForHash().get(hashKey, "hk");
    }

}
