package com.csc.spring.demo.mock.bean;

import lombok.Data;

/**
 * @Description:
 * @PackageName: com.csc.mock.bean
 * @Author: 陈世超
 * @Create: 2020-10-09 16:55
 * @Version: 1.0
 */
@Data
public class User {
    private String name;
    private int age;
    private final boolean alive = true;

    public int sum(int a, int b) {
        return a + b;
    }

    public boolean callPrivateMethod(){
        return callUser();
    }

    private boolean callUser() {
        return false;
    }

    public static boolean answer() {
        return false;
    }
}
