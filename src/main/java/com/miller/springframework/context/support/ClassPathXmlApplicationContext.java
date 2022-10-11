package com.miller.springframework.context.support;

import com.miller.springframework.beans.BeansException;

/**
 * @author miller
 * @version 1.0.0
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {
    private final String[] configLocations;

    public ClassPathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigurations() {
        return configLocations;
    }

    @Override
    public <T> T getBean(Class<T> type) throws BeansException {
        return getBeanFactory().getBean(type);
    }
}
