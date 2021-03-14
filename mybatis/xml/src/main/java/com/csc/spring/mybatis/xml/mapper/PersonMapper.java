package com.csc.spring.mybatis.xml.mapper;

import com.csc.spring.mybatis.xml.domain.Person;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 19:27
 */
@Mapper
public interface PersonMapper {
    List<Person> findAll();

    Person findById(Integer id);

    int savePerson(Person person);

    int updatePerson(Person person);

    int deletePerson(Integer id);


    /**
     * 模糊查询
     *
     * @param name %key%
     * @return
     */
    List<Person> findByName1(String name);

    /**
     * 模糊查询
     *
     * @param name key
     * @return
     */
    List<Person> findByName2(String name);


    int findTotal();


    List<Person> findPersonByCondition(Person person);

    List<Person> findPersonByIds(List<Integer> ids);

}
