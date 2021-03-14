package com.csc.spring.mybatis.xml.mapper;

import com.csc.spring.mybatis.xml.domain.Account;
import com.csc.spring.mybatis.xml.domain.AccountUser;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 22:31
 */
public interface AccountMapper {

    List<Account> findAll();

    List<AccountUser> findAllAccount();

    List<Account> findAllLazy();

    List<Account> findAccountByUid(Integer uid);
}
