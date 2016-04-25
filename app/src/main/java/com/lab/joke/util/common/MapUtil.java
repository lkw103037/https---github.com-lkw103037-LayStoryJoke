package com.lab.joke.util.common;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by luokaiwen on 15/8/14.
 * <p/>
 * 地图帮助类
 */
public class MapUtil {

    private final String TAG = MapUtil.class.getSimpleName();
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private Location mLocation;

    /**
     * 用于百度坐标和火星坐标转换的常量
     */
    private static final double x_pi = 3.14159265358979324 * 3000.0 / 180.0;

    private static final double EARTH_RADIUS = 6378137.0;

    public MapUtil() {

    }

    public MapUtil(Context context) {

        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        mLocationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) { //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
                // log it when the location changes
                if (location != null) {

                    mLocation = location;

                    LogUtil.e(TAG, "Location changed : Lat: "
                            + location.getLatitude() + " Lng: "
                            + location.getLongitude());
                }
            }

            @Override
            public void onProviderDisabled(String provider) {
                // Provider被disable时触发此函数，比如GPS被关闭
            }

            @Override
            public void onProviderEnabled(String provider) {
                //  Provider被enable时触发此函数，比如GPS被打开
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // Provider的转态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
            }
        };

        //mLocationManager.setTestProviderEnabled("gps", true);

        if (!mLocationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            //打开GPS 需Android2.2以上系统支持
            android.provider.Settings.Secure.setLocationProviderEnabled(context.getContentResolver(), LocationManager.GPS_PROVIDER, true);
        }

    }

    public void requestUpdate() {
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                1000, 10, mLocationListener);
    }

    public void removeUpdate() {
        mLocationManager.removeUpdates(mLocationListener);
    }

    /**
     * 结束使用GPS
     */
    public void disableLocation() {
        mLocationManager.removeUpdates(mLocationListener);//listener 即为监听器实例
        mLocationManager.setTestProviderEnabled("gps", false);
    }

    /**
     * 获取Location信息
     *
     * @return
     */
    public Location getLocation() {

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        String provider = mLocationManager.getBestProvider(criteria, true);
        Location location = mLocationManager.getLastKnownLocation(provider);
        if (null == mLocation) {
            mLocation = location;
        }
        //Location location = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        //LogUtil.e(TAG, "location lon:" + location.getLongitude() + " lat:" + location.getLatitude());
        return mLocation;
    }

    // 返回单位是米
    public double getDistance(double longitude1, double latitude1,
                              double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public KLocation BDToGCJ(double gcj_lat, double gcj_lon) {

        double x = gcj_lat, y = gcj_lon;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double bd_lon = z * Math.cos(theta) + 0.0065;
        double bd_lat = z * Math.sin(theta) + 0.006;

        KLocation location = new KLocation(bd_lat, bd_lon);
        return location;
    }

    public KLocation GCJToBD(double bd_lat, double bd_lon) {

        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double gcj_lon = z * Math.cos(theta);
        double gcj_lat = z * Math.sin(theta);

        KLocation location = new KLocation(gcj_lat, bd_lon);
        return location;
    }

    public class KLocation {

        /**
         * 纬度
         */
        private double lat;

        /**
         * 经度
         */
        private double lon;

        public KLocation(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        @Override
        public String toString() {
            return "Location{" +
                    "lat=" + lat +
                    ", lon=" + lon +
                    '}';
        }
    }
}
