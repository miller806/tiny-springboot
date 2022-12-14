package com.miller.springframework.beans.factory;

/**
 * @author miller
 * @version 1.0.0
 */
public interface InitializingBean {
    /**
     * Bean 属性填充之后调用
     */
    void afterPropertiesSet() throws Exception;
}
