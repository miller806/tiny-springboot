package com.miller.springframework.context;

import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.Aware;

/**
 * 实现此接口，就能感知所属的 ApplicationContext
 *
 * @author miller
 * @version 1.0.0
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
