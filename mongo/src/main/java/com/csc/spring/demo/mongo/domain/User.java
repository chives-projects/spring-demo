package com.csc.spring.demo.mongo.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @description:
 * @author: csc
 * @create: 2021/3/17 23:28
 */

/**
 * mongotemplate操作不指定collectionName时，
 *      如果不用Document指定collection的话，默认使用类名并将首字母小写
 */
@Document(collection = "User")
public class User implements Serializable {
    private Long id;

    private String name;

    private int age;

    private double weight;

    private float height;

    private boolean vip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", height=" + height +
                ", vip=" + vip +
                '}';
    }
}
