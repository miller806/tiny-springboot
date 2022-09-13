package com.miller.springframework.context.support;

import com.miller.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.miller.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author miller
 * @version 1.0.0
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configurations = getConfigurations();
        if (null != configurations) {
            xmlBeanDefinitionReader.loadBeanDefinitions(configurations);
        }
    }

    protected abstract String[] getConfigurations();
}
