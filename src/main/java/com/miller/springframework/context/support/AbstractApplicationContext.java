package com.miller.springframework.context.support;

import cn.hutool.core.bean.BeanException;
import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.miller.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.miller.springframework.beans.factory.config.BeanPostProcessor;
import com.miller.springframework.context.ApplicationEvent;
import com.miller.springframework.context.ApplicationListener;
import com.miller.springframework.context.ConfigurableApplicationContext;
import com.miller.springframework.context.event.ApplicationEventMulticaster;
import com.miller.springframework.context.event.ContextClosedEvent;
import com.miller.springframework.context.event.SimpleApplicationEventMulticaster;
import com.miller.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;


/**
 * @author miller
 * @version 1.0.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";
    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeanException {
        //1 创建BeanFactory,并加载BeanDefinition
        refreshBeanFactory();
        //2 获取BeanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        //3 添加ApplicationContextAwareProcessor,让继承自ApplicationContextAware的Bean对象能感知所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
        //4 在bean实例化之前执行BeanFactoryProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        //5 BeanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        //6 提前实例化单列Bean对象
        beanFactory.preInstantiateSingletons();
        //7 初始化事件发布者
        initApplicationEventMulticaster();
        //8 注册事件监听器
        registerListeners();
        //9 发布容器刷新完成事件
        finishRefresh();
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextClosedEvent(this));
    }

    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }


    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭时间
        publishEvent(new ContextClosedEvent(this));
        //执行销毁单列bean的销毁方法
        getBeanFactory().destroySingletons();
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
