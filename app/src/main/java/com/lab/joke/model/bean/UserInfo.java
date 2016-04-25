package com.lab.joke.model.bean;

/**
 * Created by luokaiwen on 15/5/13.
 * <p/>
 * 登录用户的信息
 */
public class UserInfo extends ResultData {

    /**
     * 用户ID
     */
    private String tid;

    /**
     * 姓名
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 电话号
     */
    private String mobilePhone;

    /**
     * 性别 0：女 1：男
     */
    private String sex;

    /**
     * 所在城市ID 如：110100
     */
    private String cityId;

    /**
     * 是否是卖家 0：不是 1：是
     */
    private String isSeller;

    // 以下在用户信息中用到
    /**
     * 密码
     */
    private String password;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 账户余额
     */
    private String account;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(String isSeller) {
        this.isSeller = isSeller;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "tid='" + tid + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", sex='" + sex + '\'' +
                ", cityId='" + cityId + '\'' +
                ", isSeller='" + isSeller + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
