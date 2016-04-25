package com.lab.joke.model.bean;

import java.io.Serializable;

/**
 * Created by luokaiwen on 15/5/7.
 * <p/>
 * 用户
 */
public class User implements Serializable {

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 标签ID
     */
    private String tid;

    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * 城市ID
     */
    private String cityId;

    /**
     * 创建时间
     */
    private String createTime;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", tid='" + tid + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", cityId='" + cityId + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
