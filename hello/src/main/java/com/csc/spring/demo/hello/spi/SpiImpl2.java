package com.csc.spring.demo.hello.spi;

/**
 * @Description:
 * @PackageName: com.csc.spi.service
 * @Author: 陈世超
 * @Create: 2020-09-08 17:01
 * @Version: 1.0
 */
public class SpiImpl2 implements SPIService {
    @Override
    public void execute() {
        System.out.println("SpiImpl2");
    }
}
