package com.csc.spring.demo.mongo;

import com.csc.spring.demo.mongo.domain.User;
import com.csc.spring.demo.mongo.repository.UserDao;
import com.csc.spring.demo.mongo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MongoApplicationTests {

    @Autowired
    private UserDao userDao;


    @Test
    void userDao() {
        List<User> userList = userDao.findAll();
        userList.forEach(System.out::print);
    }

}
