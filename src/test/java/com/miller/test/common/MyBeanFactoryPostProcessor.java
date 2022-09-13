package com.miller.test.common;

import com.miller.springframework.beans.PropertyValue;
import com.miller.springframework.beans.PropertyValues;
import com.miller.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.miller.springframework.beans.factory.config.BeanDefinition;
import com.miller.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author miller
 * @version 1.0.0
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("company", "改为:字节1"));
    }
}
