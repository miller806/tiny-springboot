package com.miller.test;

import com.miller.springframework.context.support.ClassPathXmlApplicationContext;
import com.miller.test.bean.UserService;
import org.junit.jupiter.api.Test;

/**
 * @author miller
 * @version 1.0.0
 */
public class ContextTest {
    @Test
    void test_context(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = context.getBean("userService",UserService.class);
        String result = userService.queryUserInfo();
        System.out.println("测试结果：" + result);
    }
}
