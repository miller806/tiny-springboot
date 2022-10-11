package com.miller.test.bean;

import cn.hutool.core.util.RandomUtil;
import com.miller.springframework.beans.factory.annotation.Autowired;
import com.miller.springframework.beans.factory.annotation.Value;
import com.miller.springframework.stereotype.Component;

/**
 * @author miller
 * @version 1.0.0
 */
@Component("userService")
public class UserService02 implements IUserService {
    @Value("${token}")
    private String token;
    @Autowired
    private UserDao userDao;

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(RandomUtil.randomLong(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userDao.queryUserName("10001") + "，" + token;
    }

    @Override
    public String register(String userName) {
        return null;
    }
}
