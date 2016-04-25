package com.lab.joke.data.sp;

import android.content.Context;
import android.text.TextUtils;

import com.lab.joke.model.bean.UserInfo;

/**
 * Created by luokaiwen on 15/5/16.
 * <p/>
 * 用于存放用户名和密码
 */
public class SPUser extends SPBase {

    private static final String USER = "user";
    private static final String TID = "tid";
    private static final String MOBILE_PHONE = "mobilePhone";
    private static final String NAME = "name";
    private static final String AVATAR = "avatar";
    private static final String SEX = "sex";
    private static final String CITY_ID = "cityId";
    private static final String IS_SELLER = "isSeller";
    private static final String BIRTHDAY = "birthday";

    private static final String PASSWORD = "passwrod";
    private static final String COOKIE = "cookie";

    /**
     * 获取用户信息
     */
    public static UserInfo getUserInfo(Context context) {

        // 根据是否有电话信息来判断是否已登录
        if (TextUtils.isEmpty(getMobilePhone(context))) {
            return null;
        }

        UserInfo userInfo = new UserInfo();

        userInfo.setTid(getTid(context));
        userInfo.setMobilePhone(getMobilePhone(context));
        userInfo.setName(getName(context));
        userInfo.setAvatar(getAvatar(context));
        userInfo.setSex(getSex(context));
        userInfo.setCityId(getCityId(context));
        userInfo.setIsSeller(getIsSeller(context));

        return userInfo;
    }

    /**
     * 存储用户信息
     *
     * @param userInfo
     */
    public static void setUserInfo(Context context, UserInfo userInfo) {

        if (null == userInfo) {
            return;
        }

        setTid(context, userInfo.getTid());
        setMobilePhone(context, userInfo.getMobilePhone());
        setName(context, userInfo.getName());
        setAvatar(context, userInfo.getAvatar());
        setSex(context, userInfo.getSex());
        setCityId(context, userInfo.getCityId());
        setIsSeller(context, userInfo.getIsSeller());
    }

    /**
     * 清除用户信息
     */
    public static void clear(Context context) {

        setTid(context, "");
        setMobilePhone(context, "");
        setName(context, "");
        setAvatar(context, "");
        setSex(context, "");
        setCityId(context, "");
        setIsSeller(context, "");
    }

    /**
     * 获取 TID
     */
    public static String getTid(Context context) {

        return getContent(context, USER, TID);
    }

    /**
     * 保存 TID
     */
    public static void setTid(Context context, String content) {

        setContent(context, USER, TID, content);
    }

    /**
     * 获取 MOBILE_PHONE
     */
    public static String getMobilePhone(Context context) {

        return getContent(context, USER, MOBILE_PHONE);
    }

    /**
     * 保存 MOBILE_PHONE
     */
    public static void setMobilePhone(Context context, String content) {

        setContent(context, USER, MOBILE_PHONE, content);
    }

    /**
     * 获取 NAME
     */
    public static String getName(Context context) {

        return getContent(context, USER, NAME);
    }

    /**
     * 保存 NAME
     */
    public static void setName(Context context, String content) {

        setContent(context, USER, NAME, content);
    }

    /**
     * 获取 AVATAR
     */
    public static String getAvatar(Context context) {

        return getContent(context, USER, AVATAR);
    }

    /**
     * 保存 AVATAR
     */
    public static void setAvatar(Context context, String content) {

        setContent(context, USER, AVATAR, content);
    }

    /**
     * 获取 SEX
     */
    public static String getSex(Context context) {

        return getContent(context, USER, SEX);
    }

    /**
     * 保存 SEX
     */
    public static void setSex(Context context, String content) {

        setContent(context, USER, SEX, content);
    }

    /**
     * 获取 CITY_ID
     */
    public static String getCityId(Context context) {

        return getContent(context, USER, CITY_ID);
    }

    /**
     * 保存 CITY_ID
     */
    public static void setCityId(Context context, String content) {

        setContent(context, USER, CITY_ID, content);
    }

    /**
     * 获取 生日
     */
    public static String getBirthday(Context context) {

        return getContent(context, USER, BIRTHDAY);
    }

    /**
     * 保存 生日
     */
    public static void setBirthday(Context context, String content) {

        setContent(context, USER, BIRTHDAY, content);
    }

    /**
     * 获取 IS_SELLER
     */
    public static String getIsSeller(Context context) {

        return getContent(context, USER, IS_SELLER);
    }

    /**
     * 保存 IS_SELLER
     */
    public static void setIsSeller(Context context, String content) {

        setContent(context, USER, IS_SELLER, content);
    }

    /**
     * 获取 PASSWORD
     */
    public static String getPassword(Context context) {

        return getContent(context, USER, PASSWORD);
    }

    /**
     * 保存 PASSWORD
     */
    public static void setPassword(Context context, String content) {

        setContent(context, USER, PASSWORD, content);
    }

    /**
     * 获取 COOKIE
     */
    public static String getCookie(Context context) {

        return getContent(context, USER, COOKIE);
    }

    /**
     * 保存 COOKIE
     */
    public static void setCookie(Context context, String content) {

        setContent(context, USER, COOKIE, content);
    }
}
