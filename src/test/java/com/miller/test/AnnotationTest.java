package com.miller.test;

import com.miller.springframework.context.support.ClassPathXmlApplicationContext;
import com.miller.test.bean.IPeopleService;
import com.miller.test.bean.IUserService;
import org.junit.jupiter.api.Test;

/**
 * @author miller
 * @version 1.0.0
 */
public class AnnotationTest {
    @Test
    void test_property(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        IPeopleService peopleService = applicationContext.getBean("peopleService", IPeopleService.class);
        System.out.println("测试结果：" + peopleService);
    }

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IPeopleService peopleService = applicationContext.getBean("peopleService", IPeopleService.class);
        System.out.println("测试结果：" + peopleService.queryUserInfo());
    }

    @Test
    public void test_scan01() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
