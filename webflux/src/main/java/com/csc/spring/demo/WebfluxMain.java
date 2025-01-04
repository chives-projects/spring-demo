package com.csc.spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: ${TODO}
 * @Author: csc
 * @Create: 2024-12-27
 */
@SpringBootApplication(scanBasePackages = "com.csc.spring")
public class WebfluxMain {
    public static void main(String[] args) {
        SpringApplication.run(WebfluxMain.class, args);

    }
}