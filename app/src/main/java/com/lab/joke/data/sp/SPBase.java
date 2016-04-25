package com.lab.joke.data.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by luokaiwen on 15/5/19.
 * <p/>
 * SharedPreference基类
 */
public class SPBase {

    protected static final String TAG = SPBase.class.getSimpleName();

    /**
     * 获取存储的内容
     *
     * @param context 上下文环境
     * @param tag     标签
     * @param label   内容
     * @return
     */
    public static String getContent(Context context, String tag, String label) {
        if (null == label) {
            return "";
        }
        SharedPreferences sp = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
        return sp.getString(label, "");
    }

    /**
     * 保存存储的内容
     *
     * @param context 上下文环境
     * @param tag     标签
     * @param content 内容
     */
    public static void setContent(Context context, String tag, String label, String content) {

        if (null == context) {
            return;
        }

        if (TextUtils.isEmpty(content)) {
            content = "";
        }

        SharedPreferences sp = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
        sp.edit().putString(label, checkContent(content)).commit();
    }

    /**
     * 保证内容不为null
     *
     * @param content
     * @return
     */
    private static String checkContent(String content) {

        if (TextUtils.isEmpty(content)) {
            return "";
        }

        return content;
    }

    /**
     * 清空记录
     *
     * @param context
     * @param tag
     */
    public static void clear(Context context, String tag) {

        SharedPreferences sp = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}
