package com.lab.joke.model.bean;

/**
 * Created by luokaiwen on 15/4/27.
 * <p/>
 * 请求结果响应状态码
 */
public class ResultState {

    /**
     * 响应状态
     */
    public String stateCode;

    /**
     * 响应消息
     */
    public String stateMessage;

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getStateMessage() {
        return stateMessage;
    }

    public void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

    @Override
    public String toString() {
        return "ResultStatus{" +
                "stateCode='" + stateCode + '\'' +
                ", stateMessage='" + stateMessage + '\'' +
                '}';
    }
}
