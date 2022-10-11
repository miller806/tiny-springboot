package com.miller.springframework.aop;

/**
 * 定义类匹配类,用于切点找到给定的接口和目标类
 * @author miller
 * @version 1.0.0
 */
public interface ClassFilter {
    boolean matches(Class<?> clazz);
}
