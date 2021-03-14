package com.csc.spring.mybatis.annotation;

import com.csc.spring.mybatis.annotation.domain.User;
import com.csc.spring.mybatis.annotation.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/27 22:31
 */
@SpringBootTest
//@MapperScan("com.csc.springboot.mapper")
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void findAll() {
        userMapper.findAll().forEach(user -> System.out.println(user));
    }

    @Test
    public void findAllUserAccount(){
        userMapper.findAllUserAccount().forEach(user -> {
            System.out.println(user);
            System.out.println(user.getAccounts());
        });
    }
//    @Test
//    public void findAllRoleUser(){
//        List<Role> roles = roleMapper.findAll();
//        for(Role role : roles){
//            System.out.println("---每个角色的信息----");
//            System.out.println(role);
//            System.out.println(role.getUsers());
//        }
//    }
//    @Test
//    public void findAllUserRole(){
//        List<User> users = userMapper.findAllUserRole();
//        for(User user : users){
//            System.out.println("-----每个用户的信息------");
//            System.out.println(user);
//            System.out.println(user.getRoles());
//        }
//    }
//
    @Test
    public void findAllUserAccountLazy(){
        List<User> users = userMapper.findAllUserAccount();
//        for(User user : users){
//            System.out.println("-----每个用户的信息------");
//            System.out.println(user);
//            System.out.println(user.getRoles());
//        }
    }
//
    @Test
    @Transactional
    public void testFirstLevelCache(){
        User user1 = userMapper.findById(1);
        System.out.println(user1);


        User user2 = userMapper.findById(1);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }
    @Test
//    @Transactional
    public void testSecondLevelCache(){
        User user1 = userMapper.findById(1);
        System.out.println(user1);


        User user2 = userMapper.findById(1);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    @Test
    void findById() {
        System.out.println(userMapper.findById(1));
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setUsername("modify User property");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(new Date());
        System.out.println("保存操作之前：" + user);

        userMapper.saveUser(user);
        System.out.println("保存操作之后：" + user);
    }

    @Test
    void updateUser() {
        User user = userMapper.findById(1);
        user.setAddress("河南郑州");
        int res = userMapper.updateUser(user);
        System.out.println(res);
    }

    @Test
    void deleteUser() {
        System.out.println(userMapper.deleteUser(7));
    }

    @Test
    void findByName1() {
        userMapper.findByName1("%王%").forEach(user -> System.out.println(user));
    }

    @Test
    void findByName2() {
        userMapper.findByName2("王").forEach(user -> System.out.println(user));
    }

    @Test
    void findTotal() {
        System.out.println(userMapper.findTotal());
    }

//    @Test
//    void findUserByCondition() {
//        User user = new User();
//        user.setUsername("老王");
//        user.setSex("男");
//        userMapper.findUserByCondition(user).forEach(user1 -> System.out.println(user1));
//    }
//
//    @Test
//    void findUserByIds() {
//        List<Integer> list = new ArrayList<Integer>();
//        list.add(1);
//        list.add(2);
//        list.add(6);
//        userMapper.findUserByIds(list).forEach(user -> System.out.println(user));
//    }


}
