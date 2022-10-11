package com.miller.springframework.beans.factory;

/**
 * @author miller
 * @version 1.0.0
 */
public interface FactoryBean<T> {
    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();
}
