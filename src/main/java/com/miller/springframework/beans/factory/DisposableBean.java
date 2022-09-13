package com.miller.springframework.beans.factory;

/**
 * @author miller
 * @version 1.0.0
 */
public interface DisposableBean {
    void destroy() throws Exception;
}
