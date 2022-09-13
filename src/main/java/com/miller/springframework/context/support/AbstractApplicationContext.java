package com.miller.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.miller.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.miller.springframework.beans.factory.config.BeanPostProcessor;
import com.miller.springframework.context.ConfigurableApplicationContext;
import com.miller.springframework.core.io.DefaultResourceLoader;

import java.util.Map;


/**
 * @author miller
 * @version 1.0.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    @Override
    public void refresh() throws BeanException {
        //1 创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();
        //2 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3 在bean实例化之前执行BeanFactoryProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        //4 BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        //5 提前实例化单列Bean对象
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void refreshBeanFactory();

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return this.getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return this.getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return this.getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return this.getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> type) throws BeansException {
        return this.getBeanFactory().getBean(beanName, type);
    }
}
