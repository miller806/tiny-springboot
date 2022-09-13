package com.miller.springframework.beans.factory.config;

import com.miller.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author miller
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();
}
