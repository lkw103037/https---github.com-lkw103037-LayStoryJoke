package com.lab.joke.connection.business.implement;

import android.content.Context;

import com.lab.joke.connection.WAction;
import com.lab.joke.controler.Controller;
import com.lab.joke.controler.IActionListener;
import com.lab.joke.connection.business.IAccount;

import java.util.HashMap;

/**
 * Created by luokaiwen on 15/5/6.
 * <p/>
 * 用户
 */
public class AccountImpl extends BaseImpl implements IAccount {

    public AccountImpl(Context context, IActionListener iActionListener) {
        super(context, iActionListener);
    }

    /**
     * 登录
     *
     * @param mobilePhone 手机号
     * @param password    密码
     */
    @Override
    public void login(String mobilePhone, String password) {

        HashMap<String, String> params = Controller.getParams();
        params.put("mobilePhone", mobilePhone);
        params.put("password", password);
        request(WAction.LOGIN, params, "");
    }
}
