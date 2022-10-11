package com.miller.springframework.utils;

import cn.hutool.core.util.ClassUtil;

/**
 * @author miller
 * @version 1.0.0
 */
public class ClassUtils {
    public static ClassLoader getDefaultClassLoader() {
        return ClassUtil.getClassLoader();
    }

    /**
     * Check whether the specified class name is a CGLIB-generated class.
     *
     * @param className the class name to check
     */
    public static boolean isCglibProxyClassName(String className) {
        return (className != null && className.contains("$$"));
    }

    /**
     * Check whether the specified class is a CGLIB-generated class.
     *
     * @param clazz the class to check
     */
    public static boolean isCglibProxyClass(Class<?> clazz) {
        return (clazz != null && isCglibProxyClassName(clazz.getName()));
    }

}
