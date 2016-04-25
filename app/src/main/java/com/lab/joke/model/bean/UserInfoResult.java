package com.lab.joke.model.bean;

/**
 * Created by luokaiwen on 15/5/13.
 * <p/>
 * 用户登录结果
 */
public class UserInfoResult extends Result {

    /**
     * 用户信息
     */
    private UserInfo data;

    public UserInfo getData() {
        return data;
    }

    public void setData(UserInfo data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "UserInfoResult{" +
                "data=" + data +
                '}';
    }
}
