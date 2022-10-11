package com.miller.springframework.context.event;

import com.miller.springframework.beans.factory.BeanFactory;
import com.miller.springframework.context.ApplicationEvent;
import com.miller.springframework.context.ApplicationListener;

/**
 * @author miller
 * @version 1.0.0
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {
    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
