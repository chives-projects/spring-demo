package com.csc.spring.mybatis.annotation.mapper;

import com.csc.spring.mybatis.annotation.domain.Account;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 22:31
 */
@Mapper
public interface AccountMapper {
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "uid", property = "uid"),
            @Result(column = "money", property = "money"),
            @Result(property = "user", column = "uid",
                    one = @One(select = "com.csc.spring.mybatis.annotation.mapper.UserMapper.findById",
                            fetchType = FetchType.LAZY))
    })
    List<Account> findAll();

    List<Account> findAllLazy();

    @Select("select * from account where uid = #{userId}")
    List<Account> findAccountByUid(Integer uid);
}
