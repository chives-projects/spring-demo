package com.csc.spring.mybatis.annotation;

import com.csc.spring.mybatis.annotation.domain.Account;
import com.csc.spring.mybatis.annotation.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 22:32
 */
@SpringBootTest
//@MapperScan("com.csc.springboot.mapper")
public class AccountTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    void findAll() {
//        accountMapper.findAll().forEach(account -> System.out.println(account));
        accountMapper.findAll().forEach(account -> {
            System.out.println(account);
        });
    }
    @Test
    void findAllLazy(){
        List<Account> accounts= accountMapper.findAll();
//        for(Account account : accounts){
//            System.out.println("--------每个account的信息------------");
//            System.out.println(account);
//            System.out.println(account.getUser());
//        }
    }



}
