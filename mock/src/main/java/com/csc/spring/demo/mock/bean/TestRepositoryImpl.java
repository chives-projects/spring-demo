package com.csc.spring.demo.mock.bean;

import org.springframework.stereotype.Service;

/**
 * @Description:
 * @PackageName: com.csc.mock.bean
 * @Author: 陈世超
 * @Create: 2020-10-09 16:57
 * @Version: 1.0
 */
@Service
public class TestRepositoryImpl implements TestRepository {
    @Override
    public String getName(User user) {
        return "csc";
    }
}