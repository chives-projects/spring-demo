/*
 * Copyright (c) 2015 MONKEYK Information Technology Co. Ltd
 * www.monkeyk.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * MONKEYK Information Technology Co. Ltd ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with MONKEYK Information Technology Co. Ltd.
 */
package com.csc.spring.oauth.server.infrastructure.jdbc;

import com.csc.spring.oauth.server.domain.user.User;
import com.csc.spring.oauth.server.domain.user.UserRepository;
import com.csc.spring.oauth.server.infrastructure.AbstractRepositoryTest;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.Assert.*;


/*
  * @author Shengzhao Li
  */
public class UserRepositoryJdbcTest extends AbstractRepositoryTest {


    @Autowired
    private UserRepository userRepository;


    @Test
    public void findByGuid() {
        User user = userRepository.findByGuid("oood");
        assertNull(user);

        user = new User("user", "123", "123", "ewo@honyee.cc");
        userRepository.saveUser(user);

        user = userRepository.findByGuid(user.guid());
        assertNotNull(user);
        assertNotNull(user.email());


    }

    @Test
    public void findUsersByUsername() {
        User user = userRepository.findByGuid("oood");
        assertNull(user);

        user = new User("user", "123", "123", "ewo@honyee.cc");
        userRepository.saveUser(user);

        final List<User> list = userRepository.findUsersByUsername(user.username());
        assertNotNull(list);

        assertEquals(list.size(), 1);

    }


    @Test
    public void updateUser() {
        User user = new User("user", "123", "123", "ewo@honyee.cc");
        userRepository.saveUser(user);

        user = userRepository.findByGuid(user.guid());
        assertNotNull(user);
        assertNotNull(user.email());

        String newEmail = "test@honyee.cc";
        user.email(newEmail).phone("12344444");
        userRepository.updateUser(user);

        user = userRepository.findByGuid(user.guid());
        assertNotNull(user);
        assertEquals(user.email(), newEmail);
    }


    @Test
    public void findByUsername() {
        String username = "user";
        User user = new User(username, "123", "123", "ewo@honyee.cc");
        userRepository.saveUser(user);

        User result = userRepository.findByUsername(username);
        assertNotNull(result);
    }


    /*
    * Run the test must initial db firstly
    * */
//    @Test()
    public void testPrivilege() {

        String guid = "55b713df1c6f423e842ad68668523c49";
        final User user = userRepository.findByGuid(guid);

        assertNotNull(user);
        assertEquals(user.privileges().size(), 1);


    }


}