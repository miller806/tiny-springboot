package com.miller.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import com.miller.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import com.miller.springframework.beans.factory.config.BeanDefinition;
import com.miller.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.miller.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author miller
 * @version 1.0.0
 */
public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider {
    private BeanDefinitionRegistry beanDefinitionRegistry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void doScan(String... basePackages) {
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition beanDefinition : candidates) {
                String beanScope = resolveBeanScope(beanDefinition);
                if (StrUtil.isNotEmpty(beanScope)) {
                    beanDefinition.setScope(beanScope);
                }
                beanDefinitionRegistry.registerBeanDefinition(determineBeanName(beanDefinition), beanDefinition);
            }
        }
        //注册处理注解的BeanPostProcessor
        beanDefinitionRegistry.registerBeanDefinition("internalAutowiredAnnotationProcessor",new BeanDefinition(AutowiredAnnotationBeanPostProcessor.class));
    }

    private String resolveBeanScope(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if (null != scope) {
            return scope.value();
        }
        return StrUtil.EMPTY;
    }

    private String determineBeanName(BeanDefinition beanDefinition) {
        Class<?> beanClass = beanDefinition.getBeanClass();
        Component component = beanClass.getAnnotation(Component.class);
        String value = component.value();
        if (StrUtil.isEmpty(value)) {
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }
        return value;
    }
}
