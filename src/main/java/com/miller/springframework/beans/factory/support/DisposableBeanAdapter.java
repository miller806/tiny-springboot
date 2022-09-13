package com.miller.springframework.beans.factory.support;

import com.miller.springframework.beans.factory.DisposableBean;

/**
 * @author miller
 * @version 1.0.0
 */
public class DisposableBeanAdapter implements DisposableBean {
    private final Object bean;
    private final String beanName;
    private String destroyMethodName;


    public DisposableBeanAdapter(Object bean, String beanName, String destroyMethodName) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = destroyMethodName;
    }

    @Override
    public void destroy() throws Exception {
        //1 实现接口 DisposableBean
        if(bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        //2 执行配置的destroy方法

    }
}
