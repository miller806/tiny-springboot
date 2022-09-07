package com.miller.springframework.beans.factory.support;

import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author miller
 */
public interface InstantiationStrategy {
    /**
     * 实例化bean
     *
     * @param beanDefinition bean定义
     * @param beanName       名称
     * @param constructor    构造器
     * @param args           参数
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor<?> constructor, Object[] args) throws BeansException;
}
