package com.miller.test.event;

import com.miller.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author miller
 * @version 1.0.0
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
