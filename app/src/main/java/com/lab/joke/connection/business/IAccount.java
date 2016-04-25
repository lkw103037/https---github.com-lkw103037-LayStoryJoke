package com.lab.joke.connection.business;

/**
 * Created by luokaiwen on 15/4/27.
 * <p/>
 * 用户业务接口
 */
public interface IAccount {

    /**
     * 登录
     *
     * @param mobilePhone 手机号
     * @param password    密码
     */
    void login(String mobilePhone, String password);
}
