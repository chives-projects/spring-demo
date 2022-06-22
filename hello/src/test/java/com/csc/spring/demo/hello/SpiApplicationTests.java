package com.csc.spring.demo.hello;

import com.csc.spring.demo.hello.spi.SPIService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

@SpringBootTest
class SpiApplicationTests {

    @Test
    void contextLoads() {
//        ServiceLoader<Driver> load = ServiceLoader.load(Driver.class);
//        Iterator<Driver> iterator = load.iterator();
//        while (iterator.hasNext()) {
//            Driver myDriver = iterator.next();
//            try {
//                myDriver.connect("url",new Properties());
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }

//        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
//        Iterator<SPIService> iterator = load.iterator();
//        //循环获取实现类并执行其中的方法
//        while(iterator.hasNext()) {
//            SPIService ser = iterator.next();
//            ser.execute();
//        }

        Iterator<SPIService> providers = Service.providers(SPIService.class);
        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);

        while (providers.hasNext()) {
            SPIService ser = providers.next();
            ser.execute();
        }
        System.out.println("--------------------------------");
        Iterator<SPIService> iterator = load.iterator();
        while (iterator.hasNext()) {
            SPIService ser = iterator.next();
            ser.execute();
        }
    }

}
