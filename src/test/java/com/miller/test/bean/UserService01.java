package com.miller.test.bean;


import cn.hutool.core.util.RandomUtil;

/**
 * @author miller
 * @version 1.0.0
 */
public class UserService01 implements IUserService {
    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(RandomUtil.randomLong(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "小傅哥，100001，深圳";
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(RandomUtil.randomLong(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + userName + " success！";
    }
}
