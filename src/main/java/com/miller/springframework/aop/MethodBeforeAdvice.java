package com.miller.springframework.aop;

import java.lang.reflect.Method;

/**
 *
 * @author miller
 * @version 1.0.0
 */
public interface MethodBeforeAdvice extends BeforeAdvice {
    void before(Method method, Object[] args, Object target) throws Throwable;
}
