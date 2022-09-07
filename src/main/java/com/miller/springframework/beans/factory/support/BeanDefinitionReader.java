package com.miller.springframework.beans.factory.support;

import com.miller.springframework.core.io.Resource;
import com.miller.springframework.core.io.ResourceLoader;

/**
 * @author miller
 * @version 1.0.0
 */
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource... resources);

    void loadBeanDefinitions(String location);
}
