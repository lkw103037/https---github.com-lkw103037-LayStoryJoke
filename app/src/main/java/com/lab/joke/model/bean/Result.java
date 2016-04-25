package com.lab.joke.model.bean;

/**
 * Created by luokaiwen on 15/4/27.
 * <p/>
 * 接口返回结果
 */
public class Result {

    /**
     * 响应状态码
     */
    public ResultState state;

    public String action;

    public ResultState getState() {
        return state;
    }

    public void setState(ResultState state) {
        this.state = state;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Result{" +
                "state=" + state +
                ", action='" + action + '\'' +
                '}';
    }
}
