package com.miller.springframework.context;

/**
 * @author miller
 * @version 1.0.0
 */
public interface ApplicationEventPublisher {
    void publishEvent(ApplicationEvent event);
}
