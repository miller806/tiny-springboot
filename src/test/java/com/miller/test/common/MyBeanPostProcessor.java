package com.miller.test.common;

import cn.hutool.core.bean.BeanException;
import com.miller.springframework.beans.factory.config.BeanPostProcessor;
import com.miller.test.bean.UserService;

/**
 * @author miller
 * @version 1.0.0
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeanException {
        if ("userService".equals(beanName)) {
            UserService userService = (UserService) bean;
            userService.setLocation("改为:北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeanException {
        return bean;
    }
}
