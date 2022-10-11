package com.miller.test.bean;

import com.miller.springframework.beans.BeansException;
import com.miller.springframework.beans.factory.*;
import com.miller.springframework.context.ApplicationContext;
import com.miller.springframework.context.ApplicationContextAware;

public class UserService implements IUserService, InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;
    private String uId;
    private String company;
    private String location;
    private IUserDao userDao;

    @Override
    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    @Override
    public String register(String userName) {
        return null;
    }


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("ClassLoader is " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name is " + name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
