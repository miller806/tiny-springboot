package com.miller.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @author miller
 * @version 1.0.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
    String value() default "";
}
