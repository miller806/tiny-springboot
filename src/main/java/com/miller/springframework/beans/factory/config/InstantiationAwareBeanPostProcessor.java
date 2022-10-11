package com.miller.springframework.beans.factory.config;

import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.PropertyValues;

/**
 * @author miller
 * @version 1.0.0
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {
    /**
     * 在Bean对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在Bean对象执行初始化方法之后，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    boolean postProcessAfterInstantiation(Class<?> beanClass, String beanName) throws BeansException;

    /**
     * 在Bean对象实例化完成之后，设置属性之前执行此方法
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName);
}
