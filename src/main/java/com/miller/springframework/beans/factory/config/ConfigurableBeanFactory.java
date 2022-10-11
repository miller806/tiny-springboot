package com.miller.springframework.beans.factory.config;

import com.miller.springframework.beans.factory.HierarchicalBeanFactory;
import com.miller.springframework.utils.StringValueResolver;

/**
 * @author miller
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    void destroySingletons();

    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    String resolveEmbeddedValue(String value);
}
