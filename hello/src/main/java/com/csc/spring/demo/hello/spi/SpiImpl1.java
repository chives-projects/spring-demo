package com.csc.spring.demo.hello.spi;

/**
 * @Description:
 * @PackageName: com.csc.spi.service
 * @Author: 陈世超
 * @Create: 2020-09-08 17:00
 * @Version: 1.0
 */
public class SpiImpl1 implements SPIService {
    @Override
    public void execute() {
        System.out.println("SpiImpl1 ");
    }

//    public static void main(String[] args) {
//        Iterator<SPIService> providers = Service.providers(SPIService.class);
//        ServiceLoader<SPIService> load = ServiceLoader.load(SPIService.class);
//
//        while(providers.hasNext()) {
//            SPIService ser = providers.next();
//            ser.execute();
//        }
//        System.out.println("--------------------------------");
//        Iterator<SPIService> iterator = load.iterator();
//        while(iterator.hasNext()) {
//            SPIService ser = iterator.next();
//            ser.execute();
//        }src\main\java\com\csc\spi\service\SPIService.java
//    }
}
