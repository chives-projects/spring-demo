package com.csc.spring.mybatis.xml.mapper;

import com.csc.spring.mybatis.xml.domain.QueryVo;
import com.csc.spring.mybatis.xml.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/27 22:28
 */
@Mapper
public interface UserMapper {
    List<User> findAll();

    User findById(Integer id);

    int saveUser(User user);

    int updateUser(User user);

    int deleteUser(Integer id);


    List<User> findByName1(String name);

    List<User> findByName2(String name);


    int findTotal();


    /**
     * 根据对象查询
     *
     * @param vo
     * @return
     */
    List<User> findByVo(QueryVo vo);


    List<User> findUserByCondition(User person);

    List<User> findUserByIds(List<Integer> ids);


    List<User> findAllUserAccount();

    List<User> findAllUserRole();


    List<User> findAllUserAccountLazy();


    List<User> findUserByIdArray(Integer[] ids);

    List<User> findUserByIdArray(User[] users);

    List<User> findUserByIdMap(Map<String, Object> map);
//    List<User> findUserByIdMap(Map<String, Map<String, Object>> param);

    int addBatch(List<User> list);
}
