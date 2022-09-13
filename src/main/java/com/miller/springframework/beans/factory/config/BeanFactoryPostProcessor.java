package com.miller.springframework.beans.factory.config;

import com.miller.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author miller
 * @version 1.0.0
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有benDefinition加载完成虎,在实例化Bean对象之前,提供修改BeanDefinition属性的机制
     * @param beanFactory 工厂
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);
}
