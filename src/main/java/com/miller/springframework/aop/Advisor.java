package com.miller.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * 访问者
 *
 * @author miller
 * @version 1.0.0
 */
public interface Advisor {
    Advice getAdvice();
}
