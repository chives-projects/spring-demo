package com.csc.spring.demo.mock.bean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @PackageName: com.csc.mock.bean
 * @Author: 陈世超
 * @Create: 2020-10-09 16:57
 * @Version: 1.0
 */
@Service
public class TestServiceImpl implements TestService {

//    @Autowired
//    private TestRepository testRepository;
//
//    @Override
//    public String getName(User user) {
//        String name = testRepository.getName(user);
//        return name;
//    }

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String getName(User user) {
        System.out.println("testRepository：" + testRepository.hashCode());
        System.out.println("testServiceImpl：" + this.hashCode());
        System.out.println("redisTemplate：" + redisTemplate.hashCode());
        String str = (String) redisTemplate.opsForValue().get(user.getAge());
        if (StringUtils.isNotEmpty(str)){
            return str;
        }
        String name = testRepository.getName(user);
        return name;
    }

    @Transactional
    @Override
    public String transactional() {
        String name = testRepository.getName(new User());
        return "csc";
    }


}
