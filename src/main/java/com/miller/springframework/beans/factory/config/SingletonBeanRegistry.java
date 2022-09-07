package com.miller.springframework.beans.factory.config;

/**
 * @author miller
 * @date 2022/9/5
 * @since 1.0.0
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
