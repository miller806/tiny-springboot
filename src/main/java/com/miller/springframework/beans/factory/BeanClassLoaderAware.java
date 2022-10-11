package com.miller.springframework.beans.factory;

/**
 * @author miller
 * @version 1.0.0
 */
public interface BeanClassLoaderAware extends Aware{
    void setBeanClassLoader(ClassLoader classLoader);
}
