package com.csc.spring.demo.mock.bean;

/**
 * @Description:
 * @PackageName: com.csc.mock.bean
 * @Author: 陈世超
 * @Create: 2020-10-09 16:56
 * @Version: 1.0
 */
public interface TestService {

    String getName(User user);

    String transactional();
}
