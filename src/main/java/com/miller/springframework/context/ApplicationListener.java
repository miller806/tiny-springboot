package com.miller.springframework.context;

import java.util.EventListener;

/**
 * @author miller
 * @version 1.0.0
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    void onApplicationEvent(E event);
}
