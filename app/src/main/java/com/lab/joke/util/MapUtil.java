package com.lab.joke.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.lab.joke.util.common.PackageUtil;

import java.net.URISyntaxException;

/**
 * Created by luokaiwen on 15/6/3.
 * <p/>
 * 地图帮助类
 */
public class MapUtil {

    private static final String TAG = MapUtil.class.getSimpleName();

    /**
     * 判断百度地址是否安装
     *
     * @param context
     * @return
     */
    public static boolean isBDMapExist(Context context) {

        return PackageUtil.checkPackage(context, "com.baidu.BaiduMap");
    }

    /**
     * 判断高德地图是否安装
     *
     * @param context
     * @return
     */
    public static boolean isGDMapExist(Context context) {

        return PackageUtil.checkPackage(context, "com.autonavi.minimap");
    }

    /**
     * 打开百度地图
     *
     * @param context
     * @param lat
     * @param lon
     */
    public static void openBaidu(Context context, String lat, String lon) {

        Intent intent = null;

        try {
            intent = Intent.getIntent("intent://map/marker?location=" + lat + "," + lon + "&title=私厨的位置&content=地址&coord_type=gcj02&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        context.startActivity(intent); //启动调用
    }

    /**
     * 打开高德地图
     *
     * @param context
     * @param lat
     * @param lon
     */
    public static void openGaode(Context context, String lat, String lon) {

        Intent intent = new Intent();
        intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setType(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.autonavi.minimap");
        intent.setData(Uri.parse("androidamap://viewReGeo?sourceApplication=softname&lat=" + lat + "&lon=" + lon + "&dev=0"));
        context.startActivity(intent);
    }

    /**
     * 打开提供地图服务的应用
     *
     * @param lat
     * @param lon
     */
    public static void openMap(Context context, String lat, String lon) {

        Uri uri = Uri.parse("geo:" + lat + "," + lat);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(it);
    }
}
