package com.lab.joke.util.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by luokaiwen on 15/8/20.
 * <p/>
 * Intent跳转帮助类
 */
public class IntentUtil {

    /**
     * 打开url链接
     *
     * @param context 上下文环境
     * @param url     链接
     */
    public static void loadUrl(Context context, String url) {

        if (TextUtils.isEmpty(url)) {
            return;
        }

        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
