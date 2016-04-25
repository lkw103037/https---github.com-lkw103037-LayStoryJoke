package com.lab.joke.listener;

/**
 * Created by luokaiwen on 15/8/7.
 * <p/>
 * 选择支付方式监听
 */
public interface IPayWayChangeListener {

    // 支付方式 0:支付宝, 1:微信支付
    void onPayWayChange(String payWay);
}
