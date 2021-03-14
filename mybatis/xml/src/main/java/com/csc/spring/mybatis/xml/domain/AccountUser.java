package com.csc.spring.mybatis.xml.domain;

import lombok.Data;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 22:36
 */
@Data
public class AccountUser extends Account {

    private String username;
    private String address;

    @Override
    public String toString() {
        return super.toString()+"        AccountUser{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
