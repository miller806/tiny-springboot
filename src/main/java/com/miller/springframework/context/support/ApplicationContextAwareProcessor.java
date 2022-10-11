package com.miller.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.miller.springframework.beans.factory.config.BeanPostProcessor;
import com.miller.springframework.context.ApplicationContext;
import com.miller.springframework.context.ApplicationContextAware;

/**
 * @author miller
 * @version 1.0.0
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {
    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
