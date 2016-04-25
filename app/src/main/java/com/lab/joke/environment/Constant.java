package com.lab.joke.environment;

import com.lab.joke.util.common.SDCardUtil;

/**
 * Created by luokaiwen on 15/4/28.
 * <p/>
 * 常量类
 */
public class Constant {

    public static final String IMAGE_URL = "http://img.story.com/";
    public static final String WONDER_WORLD_DIR = SDCardUtil.getSDCardPath() + "/story/";
    public static final String IMAGE_DIR = SDCardUtil.getSDCardPath() + "/story/image/";
    public static final String IMAGE_FRESCO = "/story/image/";
    public static final String REVIEW_DIR = SDCardUtil.getSDCardPath() + "/story/image/review";

    public static final String APP_ID = "2000";                     // Android应用标识
    public static final String API_VERSION = "2004";                // 接口版本号
    public static final String DEVICE_TYPE = "10041";               // 取4s的图片

    public static final String WX_APP_ID = "";    // 微信APP_ID

    /**
     * 更新间隔设置
     */
    public static final long UPDATE_INTERVAL = 12 * 60 * 60 * 1000;

    /**
     * Android Agent
     */
    public static final String AGENT = "story/" + API_VERSION;
}
