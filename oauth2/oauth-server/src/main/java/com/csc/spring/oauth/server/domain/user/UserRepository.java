package com.csc.spring.oauth.server.domain.user;

import com.csc.spring.oauth.server.domain.shared.Repository;

import java.util.List;

/**
 * @author Shengzhao Li
 */

public interface UserRepository extends Repository {

    User findByGuid(String guid);

    void saveUser(User user);

    void updateUser(User user);

    User findByUsername(String username);

    List<User> findUsersByUsername(String username);
}