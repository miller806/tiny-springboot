package com.miller.test.advice;

import com.miller.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author miller
 * @version 1.0.0
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}
