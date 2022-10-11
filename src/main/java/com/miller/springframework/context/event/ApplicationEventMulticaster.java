package com.miller.springframework.context.event;

import com.miller.springframework.context.ApplicationEvent;
import com.miller.springframework.context.ApplicationListener;

/**
 * @author miller
 * @version 1.0.0
 */
public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
