package com.miller.springframework.aop;

/**
 * @author miller
 * @version 1.0.0
 */
public interface Pointcut {
    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
