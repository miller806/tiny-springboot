package com.miller.springframework.beans.factory.support;

import com.miller.springframework.beans.factory.config.BeanDefinition;

/**
 * @author miller
 * @date 2022/9/5
 * @since 1.0.0
 */
public interface BeanDefinitionRegistry {
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
