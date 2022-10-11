package com.miller.test;

import com.miller.springframework.context.support.ClassPathXmlApplicationContext;
import com.miller.test.bean.IUserDao;
import com.miller.test.bean.UserDao;
import com.miller.test.bean.UserService;
import org.junit.jupiter.api.Test;

/**
 * @author miller
 * @version 1.0.0
 */
public class ContextTest {
    @Test
    void test_context() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        UserService userService01 = context.getBean("userService", UserService.class);
        UserService userService02 = context.getBean("userService", UserService.class);

        String result = userService01.queryUserInfo();

        System.out.println("userService01" + userService01);
        System.out.println("userService01" + userService02);

        System.out.println("测试结果：" + result);
        System.out.println("ApplicationContextAware: " + userService01.getApplicationContext());
        System.out.println("BeanFactoryAware: " + userService01.getBeanFactory());

        IUserDao proxyUserDao = context.getBean("proxyUserDao", IUserDao.class);
        System.out.println("proxyUserDao: " + proxyUserDao.queryUserName("10001"));
    }
}
