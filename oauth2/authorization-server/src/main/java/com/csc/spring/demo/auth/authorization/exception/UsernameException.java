package com.csc.spring.demo.auth.authorization.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Description: 用户名不存在异常类
 * @PackageName: com.example.temp.oauth2
 * @Author: csc
 * @Create: 2021-03-23 15:38
 * @Version: 1.0
 */
public class UsernameException extends AuthenticationException {
    public UsernameException(String message, Throwable t) {
        super(message, t);
    }

    public UsernameException(String message) {
        super(message);
    }
   /* @Override
    public int getHttpErrorCode() {
        return HttpStatusMsg.USERNAME_EXCEPTION.getStatus();
    }*/
}
