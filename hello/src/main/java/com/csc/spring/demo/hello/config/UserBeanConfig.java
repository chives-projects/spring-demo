package com.csc.spring.demo.hello.config;

import com.csc.spring.demo.hello.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: csc
 * @create: 2020/7/9 23:46
 */
@Configuration
public class UserBeanConfig {
//    @Bean
//    public Person person() {
//        return new Person(20, "lisi");
//    }

//    @Bean
//    public Person person1() {
//        return new Person(20, "lisi");
//    }

    @Bean("person2")
    public User person() {
        return new User(20, "lisi");
    }
}
