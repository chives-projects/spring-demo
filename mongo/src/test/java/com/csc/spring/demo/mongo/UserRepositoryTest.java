package com.csc.spring.demo.mongo;

import com.csc.spring.demo.mongo.domain.User;
import com.csc.spring.demo.mongo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void insert() {
        User insert = userRepository.insert(getUser());
        System.out.println(insert);
    }

    @Test
    void update() {
        User user = getUser();
        user.setName("鲁班");
        User save = userRepository.save(user);
        System.out.println(save);
    }

    private User getUser() {
        User user = new User();
        user.setId(2L);
        user.setName("韩信");
        user.setAge(22);
        user.setHeight(170.1f);
        user.setWeight(62.3d);
        user.setVip(true);
        return user;
    }

    @Test
    void find() {
        List<User> all = userRepository.findAll();
        all.forEach(System.out::print);
    }

    @Test
    void findUserById() {
        User user = userRepository.findUserById(2L);
        System.out.println(user);
    }

    @Test
    void deleteById() {
        userRepository.deleteById(2L);
    }

    @Test
    void countUsersByName() {
        int countUsersByName = userRepository.countUsersByName("韩信");
        System.out.println(countUsersByName);
    }

    @Test
    void findAllByPage() {
//        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(0, 1);
        Page<User> userPage = userRepository.findAll(pageable);
        int totalPages = userPage.getTotalPages();

        // collect 中一共有多少数据
        long totalElements = userPage.getTotalElements();
        // page 页码
        int number = userPage.getNumber();
        // 分页的话返回pageSize；否则返回content的大小
        int size = userPage.getSize();
        // content的大小
        int numberOfElements = userPage.getNumberOfElements();
        List<User> content = userPage.getContent();
        content.forEach(System.out::print);
    }

    @Test
    void findByName() {
        Order id = new Order(Sort.Direction.ASC, "_id");
        Order age = new Order(Sort.Direction.DESC, "age");
        Sort sort = Sort.by(id, age);
//        Sort.by(Sort.Direction.DESC,"age");
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<User> userPage = userRepository.findByName("韩信", pageable);
        userPage.getContent().forEach(System.out::print);
    }
}