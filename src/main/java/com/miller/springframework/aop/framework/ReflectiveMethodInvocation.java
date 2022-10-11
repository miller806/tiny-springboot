package com.miller.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @author miller
 * @version 1.0.0
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    protected final Object target;
    protected final Method method;
    protected final Object[] argments;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] argments) {
        this.target = target;
        this.method = method;
        this.argments = argments;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return argments;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, argments);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
