package com.csc.spring.demo.auth.authorization.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @Description:
 * @PackageName: com.example.temp.oauth2
 * @Author: csc
 * @Create: 2021-03-23 15:38
 * @Version: 1.0
 */
public class PasswordException extends OAuth2Exception {

    public PasswordException(String message, Throwable t) {
        super(message, t);
    }

    public PasswordException(String message) {
        super(message);
    }
    @Override
    public int getHttpErrorCode() {
        return 302;
    }
}
