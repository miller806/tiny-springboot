package com.miller.springframework.aop;

/**
 * @author miller
 * @version 1.0.0
 */
public class TargetSource {
    private final Object target;

    public TargetSource(Object object) {
        this.target = object;
    }

    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return target;
    }
}
