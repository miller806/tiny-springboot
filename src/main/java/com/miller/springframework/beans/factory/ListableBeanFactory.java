package com.miller.springframework.beans.factory;

import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Map;

/**
 * @author miller
 * @version 1.0.0
 */
public interface ListableBeanFactory extends BeanFactory{
    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * Return the names of all beans defined in this registry.
     * <p>
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
