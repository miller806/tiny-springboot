package com.miller.springframework.beans.factory;

import com.miller.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.miller.springframework.beans.factory.config.BeanDefinition;
import com.miller.springframework.beans.factory.config.BeanPostProcessor;
import com.miller.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author miller
 * @version 1.0.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    void preInstantiateSingletons();

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    BeanDefinition getBeanDefinition(String beanName);
}
