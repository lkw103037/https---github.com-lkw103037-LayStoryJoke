package com.lab.joke;

import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luokaiwen on 15/4/27.
 * <p/>
 * 美妙世界
 */
public class Joke extends Application {

    /**
     * TAG
     */
    public static final String TAG = Joke.class.getSimpleName();

    /**
     * 内存中的cookie
     */
    public static String mCookie = "";

    /**
     * 新增的cookieMap
     */
    public static Map<String, String> mCookieMap = new HashMap<String, String>();

    /**
     * 上下文环境
     */
    public static Context mConext;

    /**
     * 用于和服务器校准时间偏移量
     */
    public static long mServerTimeOffset = 0;

    @Override
    public void onCreate() {
        super.onCreate();

        mConext = getApplicationContext();

//        MobclickAgent.openActivityDurationTrack(false);
//
//        // 设置数据库的上下文
//        DBOpenHelper.setContext(this);
//
//        // 初始化Fresco
//        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder()
//                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), Constant.IMAGE_FRESCO))
//                .setBaseDirectoryName("picture")
//                .setMaxCacheSize(1024 * 1024 * 1024)
//                .build();
//        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
//                .setMainDiskCacheConfig(diskCacheConfig)
//                .build();
//        Fresco.initialize(this, config);
//
//        // 百度地图
//        SDKInitializer.initialize(this);
//
//        // 获取设备信息
//        if (BuildConfig.DEBUG) {
//            // umeng日志不加密
//            AnalyticsConfig.enableEncrypt(false);
//            String deviceInfo = Util.getDeviceInfo(getApplicationContext());
//            LogUtil.log(TAG, deviceInfo == null ? "" : deviceInfo);
//
//            // JPush设置开启日志,发布时请关闭日志
//            JPushInterface.setDebugMode(true);
//        } else {
//            // umeng日志加密
//            AnalyticsConfig.enableEncrypt(true);
//        }
//
//        // JPush
//        JPushInterface.init(this);
//
//        // 严格模式
//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());
//        }
//
//        SDCardUtil.initFileDir();
//
//        // 进入应用本地缓存的cookie放到内存中
//        mCookie = SPCookie.getCookie(this);
//
//        // 初始化CookieMap  TODO 可能会存在数组越界问题
//        if (!TextUtils.isEmpty(mCookie)) {
//
//            if (mCookie.contains(";")) {
//
//                String[] splitCookies = mCookie.split(";");
//
//                for (String splitCookie : splitCookies) {
//                    String[] tempSplitCookie = splitCookie.split("=");
//                    mCookieMap.put(tempSplitCookie[0].trim(), tempSplitCookie[1].trim());
//                    LogUtil.e(TAG, "key is " + tempSplitCookie[0].trim() + " value is " + tempSplitCookie[1].trim());
//                }
//
//            } else {
//
//                String[] splitCookies = mCookie.split("=");
//                mCookieMap.put(splitCookies[0], splitCookies[1]);
//            }
//        }

        Fresco.initialize(this);
    }

    /**
     * 设置cookie
     *
     * @param cookie
     */
//    public static void setCookie(String cookie) {
//
//        mCookie = cookie;
//
//        // 存储cookie
//        com.pengtx.story.data.sp.SPCookie.setCookie(mConext, cookie);
//
//        // 存储wwid
//        setWWID(com.pengtx.story.util.PushUtil.parseWWID(cookie));
//
//        // 从新注册JPush
//        com.pengtx.story.util.PushUtil.registJpush();
//
//        com.pengtx.story.util.common.LogUtil.e(TAG, "setCookie cookie is " + cookie);
//        com.pengtx.story.util.common.LogUtil.e(TAG, "setCookie wwid is " + com.pengtx.story.util.PushUtil.parseWWID(cookie));
//    }

    /**
     * 获取cookie
     *
     * @return
     */
    public static String getCookie() {

        return mCookie;
    }

    public static void setCookie(String cookie) {

    }

//    /**
//     * 设置WWID
//     *
//     * @param wwid
//     */
//    public static void setWWID(String wwid) {
//
//        com.lab.story.data.sp.SPCookie.setWWID(mConext, wwid);
//    }
//
//    /**
//     * 获取WWID
//     *
//     * @return 返回wwid
//     */
//    public static String getWWID() {
//
//        return com.lab.story.data.sp.SPCookie.getWWID(mConext);
//    }
}
