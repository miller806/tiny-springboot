package com.miller.springframework.beans.factory;

import com.miller.springframework.beans.BeansException;

/**
 * @author miller
 * @date 2022/9/5
 * @since 1.0.0
 */
public interface BeanFactory {
    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object... args) throws BeansException;

    <T> T getBean(String beanName, Class<T> type) throws BeansException;

    <T> T getBean(Class<T> type) throws BeansException;
}
