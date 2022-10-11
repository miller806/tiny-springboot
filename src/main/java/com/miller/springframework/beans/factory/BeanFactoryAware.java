package com.miller.springframework.beans.factory;

import com.miller.springframework.beans.BeansException;

/**
 * @author miller
 * @version 1.0.0
 */
public interface BeanFactoryAware extends Aware {
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
