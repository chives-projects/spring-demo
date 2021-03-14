package com.csc.spring.demo.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.csc.spring.demo.mybatisplus.domain.User;
import com.csc.spring.demo.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/27 22:31
 */
@SpringBootTest
@MapperScan("com.csc.spring.demo.mybatisplus.mapper")
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void findAll() {
        userMapper.selectList(null)
                .forEach(System.out::println);
    }

    @Test
    void findById() {
        System.out.println(userMapper.selectById(1));
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setUsername("modify User property");
        user.setAddress("北京市顺义区");
        user.setSex("男");
        user.setBirthday(LocalDateTime.now());
        System.out.println("保存操作之前：" + user);

        userMapper.insert(user);
        System.out.println("保存操作之后：" + user);
    }

    @Test
    void updateUser() {
//        User user = new User();
//        user.setId(7);
//        user.setAddress("河南郑州");
//        int res = userMapper.updateById(user);
//        System.out.println(res);

//        Map<String,Object> columnMap = new HashMap<>();
//        columnMap.put("id","7");//写表中的列名
//        columnMap.put("sex","男");
//        List<User> employees = userMapper.selectByMap(columnMap);
//        System.out.println(employees.size());

//        List<Integer> idList = new ArrayList<>();
//        idList.add(1);
//        idList.add(2);
//        idList.add(3);
//        List<User> employees = userMapper.selectBatchIds(idList);
//        System.out.println(employees);

//        List<User> employees = (List<User>)
        IPage page=userMapper.selectPage(new Page<>(1,2),null);
//        System.out.println(employees);

    }


}
