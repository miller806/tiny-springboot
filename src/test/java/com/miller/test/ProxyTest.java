package com.miller.test;

import com.miller.springframework.aop.MethodMatcher;
import com.miller.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.miller.test.bean.IUserService;
import com.miller.test.bean.UserService;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author miller
 * @version 1.0.0
 */
public class ProxyTest {
    @Test
    void test_proxy_method() {
//        Object targetObj = new UserService();
//        IUserService proxy = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObj.getClass().getInterfaces(), new InvocationHandler() {
//            //方法匹配器
//            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.miller.test.bean.IUserService.*(..))");
//
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        });
    }

    @Test
    void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.miller.test.bean.IUserService.*(..))");
        Class<UserService> clazz = UserService.class;
        Method method = clazz.getMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(method, clazz));
    }
}
