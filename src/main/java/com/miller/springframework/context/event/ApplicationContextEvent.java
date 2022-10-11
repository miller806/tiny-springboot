package com.miller.springframework.context.event;

import com.miller.springframework.context.ApplicationContext;
import com.miller.springframework.context.ApplicationEvent;

/**
 * @author miller
 * @version 1.0.0
 */
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
