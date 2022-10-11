package com.miller.test;

import com.miller.springframework.context.support.ClassPathXmlApplicationContext;
import com.miller.test.event.CustomEvent;
import org.junit.jupiter.api.Test;

/**
 * @author miller
 * @version 1.0.0
 */
public class EventTest {
    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "成功了！"));
        applicationContext.registerShutdownHook();
    }
}
