package com.miller.springframework.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author miller
 * @version 1.0.0
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
