package com.miller.test;

import com.miller.springframework.aop.AdvisedSupport;
import com.miller.springframework.aop.TargetSource;
import com.miller.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.miller.springframework.aop.framework.Cglib2AopProxy;
import com.miller.springframework.aop.framework.JdkDynamicAopProxy;
import com.miller.springframework.context.support.ClassPathXmlApplicationContext;
import com.miller.test.bean.IUserService;
import com.miller.test.bean.UserService;
import com.miller.test.bean.UserService01;
import org.junit.jupiter.api.Test;

/**
 * @author miller
 * @version 1.0.0
 */
public class AopTest {
    @Test
    void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService01();

        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserService01Interceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.miller.test.bean.IUserService.*(..))"));

        // 代理对象(JdkDynamicAopProxy)
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();

        // 测试调用
        System.out.println("测试结果：" + proxy_jdk.queryUserInfo());


        // 代理对象(Cglib2AopProxy)
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        // 测试调用
        System.out.println("测试结果：" + proxy_cglib.register("花花"));
    }

    @Test
    void test_aop(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:aop.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
