package com.miller.test;

import com.miller.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.miller.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.miller.test.bean.UserService;
import com.miller.test.common.MyBeanFactoryPostProcessor;
import com.miller.test.common.MyBeanPostProcessor;
import org.junit.jupiter.api.Test;

/**
 * @author miller
 * @version 1.0.0
 */
public class XmlTest {
    /**
     * 不应用上下文
     */
    @Test
    void test_xml() {
        //1 初始化工厂
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        //2 读取配置文件\注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        //3 BeanDefinition 加载完成&Bean实例化之前,修改BeanDefinition的属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(factory);
        //4 Bean实例化之后,修改Bean属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        factory.addBeanPostProcessor(beanPostProcessor);
        //5 获取bean并调用方法
        UserService userService = (UserService) factory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
    }
}
