package com.csc.spring.mybatis.xml.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: csc
 * @create: 2020/5/28 19:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {

    private Integer id;
    private String name;
    private Date birth;
    private String Sex;
    private String address;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", Sex='" + Sex + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
