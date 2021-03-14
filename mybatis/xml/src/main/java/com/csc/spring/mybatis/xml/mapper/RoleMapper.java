package com.csc.spring.mybatis.xml.mapper;

import com.csc.spring.mybatis.xml.domain.Role;

import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 23:09
 */
public interface RoleMapper {
    List<Role> findAll();
}
