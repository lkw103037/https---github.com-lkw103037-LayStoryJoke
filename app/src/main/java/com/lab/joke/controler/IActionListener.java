package com.lab.joke.controler;

import com.lab.joke.model.bean.Result;

/**
 * Created by luokaiwen on 15/5/7.
 * <p/>
 * UI界面请求监听
 */
public interface IActionListener {

    public void onActionSucc(Result resutl);

    public void onActionFail(Result result);
}
