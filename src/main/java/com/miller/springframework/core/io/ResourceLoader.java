package com.miller.springframework.core.io;

/**
 * @author miller
 * @version 1.0.0
 */
public interface ResourceLoader {
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
