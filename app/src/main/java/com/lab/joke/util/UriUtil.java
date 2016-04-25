package com.lab.joke.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.text.TextUtils;

import com.lab.joke.environment.Constant;

import java.io.File;

/**
 * Created by luokaiwen on 15/5/12.
 * <p/>
 * Uri帮助类
 */
public class UriUtil {

    public static Uri getImage(String url) {

        if (TextUtils.isEmpty(url)) {
            url = "";
        }

        return Uri.parse(Constant.IMAGE_URL + url);
    }

    public static Uri getPicUri(File file) {

        return Uri.fromFile(file);
    }

    public static boolean checkNetworkConnection(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isAvailable())
            return true;
        else
            return false;
    }
}
