package com.miller.test;

import com.miller.springframework.beans.PropertyValue;
import com.miller.springframework.beans.PropertyValues;
import com.miller.springframework.beans.factory.config.BeanDefinition;
import com.miller.springframework.beans.factory.config.BeanReference;
import com.miller.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.miller.test.bean.UserDao;
import com.miller.test.bean.UserService;
import org.junit.jupiter.api.Test;

public class ApiTest {
    @Test
    void testBeanFactory() {
        //1. 初始化工厂
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //2. 注册dao
        beanFactory.registerBeanDefinition("userDao", new BeanDefinition(UserDao.class));
        //3. UserService 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10002"));
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));
        //4. UserService注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        //5. UserService获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
