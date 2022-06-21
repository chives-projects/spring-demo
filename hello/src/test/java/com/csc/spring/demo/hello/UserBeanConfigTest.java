package com.csc.spring.demo.hello;

import com.csc.spring.demo.hello.config.UserBeanConfig;
import com.csc.spring.demo.hello.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @description:
 * @author: csc
 * @create: 2020/7/9 23:47
 */
public class UserBeanConfigTest {
    @Test
    public void xmlTest() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Object user = applicationContext.getBean("user");
        System.out.println(user);
    }

    @Test
    public void Annotation() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(UserBeanConfig.class);
        User bean = annotationConfigApplicationContext.getBean(User.class);
        System.out.println(bean);
        System.out.println(annotationConfigApplicationContext.getBeanDefinitionNames());
        String[] beanNamesForType = annotationConfigApplicationContext.getBeanNamesForType(User.class);
        for (String s : beanNamesForType)
            System.out.println(s);
    }
}
