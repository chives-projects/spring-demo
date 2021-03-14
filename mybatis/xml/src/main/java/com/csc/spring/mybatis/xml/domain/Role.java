package com.csc.spring.mybatis.xml.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 23:08
 */
@Data
public class Role implements Serializable {

    private Integer roleId;
    private String roleName;
    private String roleDesc;

    //多对多的关系映射：一个角色可以赋予多个用户
    private List<User> users;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                '}';
    }
}
