package com.miller.springframework.context;

import cn.hutool.core.bean.BeanException;

/**
 * @author miller
 * @version 1.0.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * 刷新容器
     *
     * @throws BeanException
     */
    void refresh() throws BeanException;

    void registerShutdownHook();


    void close();
}
