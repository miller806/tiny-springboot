package com.miller.springframework.beans.factory.support;

import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.miller.springframework.beans.factory.config.BeanDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author miller
 * @date 2022/9/5
 * @since 1.0.0
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName) != null;
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> map = new HashMap<>();
        beanDefinitionMap.forEach(((name, beanDefinition) -> {
            Class clazz = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(clazz)) {
                map.put(name, (T) getBean(name));
            }
        }));
        return map;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }

    @Override
    public <T> T getBean(Class<T> type) throws BeansException {
        List<String> beanNames = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class beanClass = entry.getValue().getBeanClass();
            if (type.isAssignableFrom(beanClass)) {
                beanNames.add(entry.getKey());
            }
            if (1 == beanNames.size()) {
                return getBean(beanNames.get(0), type);
            }
        }
        throw new BeansException(type + "expected single bean but found " + beanNames.size() + ": " + beanNames);
    }
}
