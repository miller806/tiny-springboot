package com.miller.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author miller
 * @version 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {
    String value() default "singleton";
}
