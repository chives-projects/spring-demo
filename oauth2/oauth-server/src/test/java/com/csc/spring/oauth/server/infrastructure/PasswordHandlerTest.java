package com.csc.spring.oauth.server.infrastructure;


//import org.junit.Test;
//
//import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
  * @author Shengzhao Li
  */
public class PasswordHandlerTest {


    @Test
    public void testMd5() throws Exception {

        final String md5 = PasswordHandler.encode("123456");
        assertNotNull(md5);
        System.out.println(md5);
    }
}