package com.miller.springframework.beans.factory;

/**
 * @author miller
 * @version 1.0.0
 */
public interface BeanNameAware extends Aware {
    void setBeanName(String name);
}
