package com.lab.joke.controler;

import android.content.Context;

import com.lab.joke.connection.business.IAccount;
import com.lab.joke.connection.business.implement.AccountImpl;
import com.lab.joke.environment.Constant;

import java.util.HashMap;

/**
 * Created by luokaiwen on 15/5/6.
 * <p/>
 * 控制器
 */
public class Controller {

    private Context mContext;
    private IAccount iAccount;

    public Controller(Context context) {

        mContext = context;
    }

    public static HashMap<String, String> getParams() {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("appID", Constant.APP_ID);
        params.put("deviceType", Constant.DEVICE_TYPE);
        params.put("apiVersion", Constant.API_VERSION);
        return params;
    }

    public IAccount getAccount(Context context, IActionListener iActionListener) {

        if (null == iAccount) {
            iAccount = new AccountImpl(context, iActionListener);
        }

        return iAccount;
    }
}
