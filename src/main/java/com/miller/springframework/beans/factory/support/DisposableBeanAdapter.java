package com.miller.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.DisposableBean;
import com.miller.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author miller
 * @version 1.0.0
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;


    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        //1 实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }
        //2 执行配置的destroy方法
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "distroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
