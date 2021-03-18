package com.csc.spring.demo.mongo.repository;

import com.csc.spring.demo.mongo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @description: 类似于 Spring Data JPA
 * @author: csc
 * @create: 2021/3/19 0:03
 */
@Component
public interface UserRepository extends MongoRepository<User, Long> {

    User findUserById(Long id);

    int countUsersByName(String name);

    Page<User> findByName(String name, Pageable pageable);
}
