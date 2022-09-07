package com.miller.springframework.beans.factory.support;

import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.BeanFactory;
import com.miller.springframework.beans.factory.config.BeanDefinition;

/**
 * @author miller
 * @date 2022/9/5
 * @since 1.0.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBean(beanName, new Object[]{});
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition, args);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
