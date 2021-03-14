package com.csc.spring.mybatis.annotation.mapper;

import com.csc.spring.mybatis.annotation.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/27 22:28
 */
@Mapper
@CacheNamespace(blocking = true)
public interface UserMapper {
    @Select("select * from user")
    @ResultMap("userMap")
    List<User> findAll();

        @Select("select * from user  where id=#{id} ")
//    @Select({"<script>",
//            "select * from user where id=#{id}",
//            "<WHERE>",
//            "   <if test=\"id!=null\">",
//            "       and id=#{id}",
//            "   </if>",
//            "</WHERE>",
//            "</script>"})
    User findById(Integer id);

    @Insert("insert into user(username,address,sex,birthday)values(#{username},#{address},#{sex},#{birthday})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = int.class, before = false,
            statement = "select last_insert_id()")
    int saveUser(User user);

    @Update("update user set username=#{username},sex=#{sex},birthday=#{birthday},address=#{address} where id=#{id}")
    int updateUser(User user);

    @Delete("delete from user where id=#{id} ")
    int deleteUser(Integer id);

    @Select("select * from user where username like #{username} ")
    @ResultMap("userMap")
    List<User> findByName1(String name);

    @Select("select * from user where username like '%${value}%' ")
    List<User> findByName2(String name);

    @Select("select count(*) from user ")
    int findTotal();

    List<User> findUserByCondition(User person);

    List<User> findUserByIds(List<Integer> ids);

    @Select("select * from user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "address", property = "address"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "birthday", property = "birthday"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "com.csc.spring.mybatis.annotation.mapper.AccountMapper.findAccountByUid",
                            fetchType = FetchType.LAZY))
    })
    List<User> findAllUserAccount();

    List<User> findAllUserRole();

    List<User> findAllUserAccountLazy();
}
