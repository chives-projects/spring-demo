package com.csc.spring.demo.mongo;

import com.csc.spring.demo.mongo.domain.User;
import com.csc.spring.demo.mongo.repository.UserDao;
import com.mongodb.client.result.DeleteResult;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

@SpringBootTest
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    void save() {
//        User save = userDao.save(getUser());
//        System.out.println(save);
        User insert = userDao.insert(getUser());
    }

    private User getUser() {
        User user = new User();
        user.setId(1L);
        user.setName("韩信");
        user.setAge(22);
        user.setHeight(170.1f);
        user.setWeight(62.3d);
        user.setVip(true);
        return user;
    }

    @Test
    void delUserById() {
//        DeleteResult deleteResult = userDao.delUserById(getUser().getId());
//        System.out.println(deleteResult.getDeletedCount());
//        System.out.println(deleteResult.wasAcknowledged());
        DeleteResult deleteResult = userDao.delAll();
        System.out.println(deleteResult.getDeletedCount());
        System.out.println(deleteResult.wasAcknowledged());
    }

    @Test
    void saveBatchUser() {
        List<User> userList = Lists.newArrayList();
        userList.add(getUser());
        User user = getUser();
        user.setId(2L);
        userList.add(user);

        Collection<User> userCollection = userDao.saveBatchUser(userList);
        userCollection.forEach(System.out::print);
    }


    @Test
    void upadteUserById() {
        User user = getUser();
        user.setName("鲁班");
        System.out.println(userDao.upadteUserById(user));
    }

    @Test
    void findUserByName() {
        User user = userDao.findUserByName("韩信");
        System.out.println(user);
    }

    @Test
    void findAll() {
        userDao.findAll().forEach(System.out::print);
    }

    @Test
    void findUserByLikeName() {
        userDao.findUserByLikeName("韩").forEach(System.out::print);
    }
    @Test
    void Aggregation() {
        userDao.Aggregation().forEach(System.out::print);
    }
}