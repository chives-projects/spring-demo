package com.csc.spring.mybatis.xml;

import com.csc.spring.mybatis.xml.domain.Person;
import com.csc.spring.mybatis.xml.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 19:39
 */
@SpringBootTest
public class PersonTest {
    @Autowired
    private PersonMapper personMapper;

    @Test
    void findPerson(){
        personMapper.findAll().forEach(person -> System.out.println(person));
    }

    @Test
    void findById() {
        System.out.println(personMapper.findById(1));
    }

    @Test
    void savePerson() {
        Person person = new Person();
        person.setName("modify Person property");
        person.setAddress("北京市顺义区");
        person.setSex("男");
        person.setBirth(new Date());
        System.out.println("保存操作之前：" + person);

        personMapper.savePerson(person);
        System.out.println("保存操作之后：" + person);
    }

    @Test
    void updatePerson() {
        Person person = personMapper.findById(1);
        person.setAddress("河南郑州");
        int res = personMapper.updatePerson(person);
        System.out.println(res);
    }

    @Test
    void deletePerson(){
        System.out.println(personMapper.deletePerson(7));
    }

    @Test
    void findByName1(){
        personMapper.findByName1("%王%").forEach(person -> System.out.println(person));
    }

    @Test
    void findByName2(){
        personMapper.findByName2("王").forEach(person -> System.out.println(person));
    }

    @Test
    void findTotal(){
        System.out.println(personMapper.findTotal());
    }

    @Test
    void findPersonByCondition(){
        Person person = new Person();
        person.setName("老王");
        person.setSex("男");
        personMapper.findPersonByCondition(person).forEach(person1 -> System.out.println(person1));
    }

    @Test
    void findPersonByIds(){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(6);
        personMapper.findPersonByIds(list).forEach(person -> System.out.println(person));
    }
}
