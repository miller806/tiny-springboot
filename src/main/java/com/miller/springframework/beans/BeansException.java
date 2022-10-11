package com.miller.springframework.beans;

/**
 * @author miller
 * @date 2022/9/5
 * @since 1.0.0
 */
public class BeansException extends RuntimeException{
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
