package com.miller.springframework.beans.factory.config;

/**
 * @author miller
 * @date 2022/9/5
 * @since 1.0.0
 */
public class BeanDefinition {
    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
