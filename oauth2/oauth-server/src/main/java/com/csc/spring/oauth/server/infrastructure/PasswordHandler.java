package com.csc.spring.oauth.server.infrastructure;

import com.csc.spring.oauth.server.web.context.SOSContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 2016/3/25
 *
 * @author Shengzhao Li
 */
public abstract class PasswordHandler {


//    private PasswordEncoder passwordEncoder = SOSContextHolder.getBean(PasswordEncoder.class);


    private PasswordHandler() {
    }


    public static String encode(String password) {
        PasswordEncoder passwordEncoder = SOSContextHolder.getBean(PasswordEncoder.class);
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
