package com.csc.spring.mybatis.xml;

import com.csc.spring.mybatis.xml.domain.QueryVo;
import com.csc.spring.mybatis.xml.domain.Role;
import com.csc.spring.mybatis.xml.domain.User;
import com.csc.spring.mybatis.xml.mapper.RoleMapper;
import com.csc.spring.mybatis.xml.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/27 22:31
 */
@SpringBootTest
@MapperScan("com.csc.spring.mybatis.xml.mapper")
public class UserTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Test
    void findAll() {
        PageHelper.startPage(2, 2);
        List<User> userList = userMapper.findAll();
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        System.out.println(userPageInfo);
//        userMapper.findAll().forEach(user -> System.out.println(user));
    }

    @Test
    public void findAllUserAccount() {
        userMapper.findAllUserAccount().forEach(user -> {
            System.out.println(user);
            System.out.println(user.getAccounts());
        });
    }

    @Test
    public void findAllRoleUser() {
        List<Role> roles = roleMapper.findAll();
        for (Role role : roles) {
            System.out.println("---每个角色的信息----");
            System.out.println(role);
            System.out.println(role.getUsers());
        }
    }

    @Test
    public void findAllUserRole() {
        List<User> users = userMapper.findAllUserRole();
        for (User user : users) {
            System.out.println("-----每个用户的信息------");
            System.out.println(user);
            System.out.println(user.getRoles());
        }
    }

    @Test
    public void findAllUserAccountLazy() {
        List<User> users = userMapper.findAllUserAccountLazy();
//        for(User user : users){
//            System.out.println("-----每个用户的信息------");
//            System.out.println(user);
//            System.out.println(user.getRoles());
//        }
    }

    @Test
    @Transactional
    public void testFirstLevelCache() {
        User user1 = userMapper.findById(1);
        System.out.println(user1);


        User user2 = userMapper.findById(1);
        System.out.println(user2);

        System.out.println(user1 == user2);
    }

    @Test
//    @Transactional
    public void testSecondLevelCache() {
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
//        User user = userMapper.findById(6);
        User user = new User();
        user.setId(6);
        user.setAddress("sahnghai");
        user.setBirthday(new Date());
        int res = userMapper.updateUser(user);
        System.out.println(res);
    }

    @Test
    void deleteUser() {
        System.out.println(userMapper.deleteUser(8));
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

    @Test
    void findByVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%王%");
        vo.setUser(user);
        userMapper.findByVo(vo).forEach(user1 -> System.out.println(user1));
    }

    @Test
    void findUserByCondition() {
        User user = new User();
//        user.setUsername("老王");
//        user.setAddress("sahnghai");
//        user.setSex("男");
        userMapper.findUserByCondition(user).forEach(user1 -> System.out.println(user1));
    }

    @Test
    void findUserByIds() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(6);
        userMapper.findUserByIds(list).forEach(user -> System.out.println(user));
    }

    @Test
    void findUserByIdArray() {
        Integer[] ids = new Integer[]{1, 3, 5};
        User[] users = new User[]{
                new User(1),
                new User(2),
                new User(5)
        };
        userMapper.findUserByIdArray(ids).forEach(user -> System.out.println(user));
    }

    @Test
    void findUserByIdMap() {
        Map<String, Map<String, Object>> param = new HashMap<String, Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("sex", "男");
        param.put("keys", map);
        userMapper.findUserByIdMap(map).forEach(user -> System.out.println(user));
    }

    @Test
    void addBatch() {
        List<User> list = new ArrayList<User>() {{
            add(new User("11", "男"));
            add(new User("22", "女"));
            add(new User("33", "男"));
            add(new User("44", "女"));
        }};
        System.out.println(userMapper.addBatch(list));

    }
}
