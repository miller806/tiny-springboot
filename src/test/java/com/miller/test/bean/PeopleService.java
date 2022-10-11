package com.miller.test.bean;

import cn.hutool.core.util.RandomUtil;
import com.miller.springframework.beans.factory.annotation.Value;
import com.miller.springframework.stereotype.Component;

/**
 * @author miller
 * @version 1.0.0
 */
@Component("peopleService")
public class PeopleService implements IPeopleService {

    @Value("${token}")
    private String token;

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

    @Override
    public String toString() {
        return "UserService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
