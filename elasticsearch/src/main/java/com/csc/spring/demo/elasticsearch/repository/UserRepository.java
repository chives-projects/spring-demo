package com.csc.spring.demo.elasticsearch.repository;

import com.csc.spring.demo.elasticsearch.pojo.User;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/27 0:06
 */
public interface UserRepository extends ElasticsearchRepository<User,Integer> {
    User findByName(String name);
    List<User> findByNameLike(String name);

    @Query("{\"bool\" : {\"must\" : {\"term\" : {\"age\" : \"?0\"}}}}")
    User findByAge(Integer age);
}
