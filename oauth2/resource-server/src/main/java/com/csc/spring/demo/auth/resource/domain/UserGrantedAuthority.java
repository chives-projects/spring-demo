package com.csc.spring.demo.auth.resource.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.HashMap;
import java.util.Map;

public class UserGrantedAuthority implements GrantedAuthority {
    private Map<String, Object> authoritys = new HashMap<>();

    public UserGrantedAuthority(String name, Object value) {
        authoritys.put(name, value);
    }

    @Override
    public String getAuthority() {
        return authoritys.toString();
    }
}
