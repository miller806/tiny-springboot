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
}
