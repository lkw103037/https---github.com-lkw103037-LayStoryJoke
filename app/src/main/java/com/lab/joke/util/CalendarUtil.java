package com.lab.joke.util;

import com.lab.joke.singleton.WWData;
import com.lab.joke.util.common.LogUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 时间帮助类
 */
public class CalendarUtil {

    private static final String TAG = CalendarUtil.class.getSimpleName();

    private static SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static SimpleDateFormat mFormatDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    private static SimpleDateFormat mFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    private static SimpleDateFormat mFormatRetain = new SimpleDateFormat("dd天HH时mm分ss秒", Locale.getDefault());
    private static SimpleDateFormat mFormatDayPoint = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
    private static SimpleDateFormat mFormatHour = new SimpleDateFormat("HH:mm", Locale.getDefault());
    private static SimpleDateFormat mFormatOrderTimeFormat = new SimpleDateFormat("MM-ddEHH:mm", Locale.getDefault());
    private static SimpleDateFormat mFormatOrderTimeParse = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
    private static SimpleDateFormat mFormatDay = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public static String getDateTimeString(Date date, SimpleDateFormat df) {
        df.setLenient(false);
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String s = df.format(date);
        return s;
    }

    public static String parseTime(SimpleDateFormat df, String formatTime) {
        df.setLenient(false);
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = null;
        try {
            date = df.parse(formatTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (null == date) {
            return "";
        }

        long timeL = date.getTime();

        String timestamp = String.valueOf(timeL / 1000);
        return timestamp;
    }

    /**
     * 剩余时间
     *
     * @return
     */
    public static String remainTime(long endTime, long currentTime) {

        if (endTime > currentTime) {

            long remain = endTime - currentTime;
            long remainS = remain % 60;
            long remainM = remain / 60 % 60;
            long remainH = remain / 60 / 60 % 24;
            long remainD = remain / 60 / 60 / 24 % 24;
            return remainD + "天" + remainH + "时" + remainM + "分" + remainS + "秒";
        } else {
            return "0秒";
        }
    }

    /**
     * 剩余时间
     *
     * @return
     */
    public static String remainTime(long remain) {

        remain = remain / 1000;

        if (remain > 0) {
            long remainS = remain % 60;
            long remainM = remain / 60 % 60;
            long remainH = remain / 60 / 60 % 24;
            long remainD = remain / 60 / 60 / 24 % 24;

            String remainTime = "";

            if (remainD != 0) {
                remainTime = remainD + "天" + remainH + "时" + remainM + "分" + remainS + "秒";
            } else if (remainH != 0) {
                remainTime = remainH + "时" + remainM + "分" + remainS + "秒";
            } else if (remainM != 0) {
                remainTime = remainM + "分" + remainS + "秒";
            } else if (remainS != 0) {
                remainTime = remainS + "秒";
            }

            return remainTime;
        } else {
            return "0秒";
        }
    }

    /**
     * 格式化时间 2014-03-31 11:41:11
     */
    public static String parseNormal(String formatTime) {
        return parseTime(mFormat, formatTime);
    }

    /**
     * 格式化时间 2014-03-31 11:41:11
     */
    public static long parseNormalLong(String formatTime) {
        return Long.parseLong(parseTime(mFormat, formatTime));
    }

    /**
     * 格式化时间 11:41
     */
    public static long parseHour(String formatTime) {

        mFormatHour.setLenient(false);
        mFormatHour.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = null;
        try {
            date = mFormatHour.parse(formatTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (null == date) {
            return 0;
        }

        return date.getTime() / 1000;
    }

    /**
     * 秒转化为小时
     *
     * @param hour
     * @return
     */
    public static String formatHour(long hour) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(hour * 1000);
        return getDateTimeString(calendar.getTime(), mFormatHour);
    }

    /**
     * 获取当前小时
     *
     * @return
     */
    public static String getCurHourStr() {

        return mFormatHour.format(new Date());
    }

    /**
     * 格式化时间 2014-03-31 11:41:11
     */
    public static String formatDate(long time) {
        if (time == 0) {
            return "";
        }

        long time_long = Long.parseLong(time + "000");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_long);
        String formatTime = getDateTimeString(calendar.getTime(), mFormat);
        return formatTime;
    }

    /**
     * 格式化时间 2014-03-31 11:41:11
     */
    public static String getCreateDate() {

        long time_long = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_long);
        String formatTime = getDateTimeString(calendar.getTime(), mFormat);
        return formatTime;
    }

    /**
     * 格式化时间 2014-03-31
     */
    public static String getCurrentDate() {

        long time_long = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_long);
        String formatTime = getDateTimeString(calendar.getTime(), mFormatDate);
        return formatTime;
    }

    /**
     * 格式化时间 2014-03-31 11:41:11
     */
    public static String getCreateNoSencond() {

        long time_long = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_long);
        String formatTime = getDateTimeString(calendar.getTime(), mFormat2);
        return formatTime;
    }

    /**
     * 格式化时间 2014.03.31
     */
    public static String formatDayPoint(long time) {
        long time_long = Long.parseLong(time + "000");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time_long);
        String formatTime = getDateTimeString(calendar.getTime(), mFormatDayPoint);
        return formatTime;
    }

    /**
     * 格式化时间 2天3时5分44秒
     */
    public static String format(long time) {
        return convert(time);
    }

    /**
     * 格式化拍品剩余时间
     *
     * @param time
     * @return
     */
    public static String formatAuctionTime(long time) {
        String formatDateTime = convert(time);

        return formatDateTime;
    }

    /**
     * 将时间(单位ms)
     *
     * @param time
     * @return
     */
    private static String convert(long time) {
        if (time < 0) {
            time = 0;
            return "";
        }
        time /= 1000;
        int day = (int) (time / 60 / 60 / 24);
        int hour = (int) (time / 60 / 60 % 24);
        int min = (int) (time / 60 % 60);
        int second = (int) (time % 60);

        String dateTime = "";

        if (day != 0) {
            dateTime = dateTime + day + "天";
        }

        if (hour != 0) {
            dateTime = dateTime + hour + "时";
        }

        if (min != 0) {
            dateTime = dateTime + min + "分";
        }

        if (second != 0) {
            dateTime = dateTime + second + "秒";
        }

        return dateTime;
    }

    /**
     * 获取当前毫秒时间
     *
     * @return
     */
    public static long getCurrentTimeMillis() {

        return System.currentTimeMillis() + WWData.INSTANCE.getServerTime();
    }

    public static void getCurrentTime() {
        Calendar c = Calendar.getInstance();
        LogUtil.e("当前时间", "当前时间" + formatDate(c.getTimeInMillis() / 1000));
        c.add(Calendar.MONTH, -6);
        LogUtil.e("当前时间", "后时间" + formatDate(c.getTimeInMillis() / 1000));
    }

    /**
     * 获取六个月前时间
     *
     * @return
     */
    public static String getSixAgo() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -6);
        return formatDate(c.getTimeInMillis() / 1000);
    }

    /**
     * 获取一年前时间
     *
     * @return
     */
    public static String getOneYearAgo() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -12);
        return formatDate(c.getTimeInMillis() / 1000);
    }

    /**
     * 获取年
     *
     * @return 年
     */
    public static int getYearInt() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    /**
     * 获取月
     *
     * @return 一年中的第几个月
     */
    public static int getMonthOfYearInt() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int month = calendar.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 获取日
     *
     * @return 一个月中的第几天
     */
    public static int getDayOfMonthInt() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 解析格式化的时间
     *
     * @param time   时间
     * @param format 格式
     * @return
     */
    public static long parseTime(String time, SimpleDateFormat format) {

        Date date = null;

        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (null == date) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * 解析格式化的时间
     *
     * @param time 时间
     * @return
     */
    public static long parseTime(String time) {

        return parseTime(time, mFormat);
    }

    /**
     * 格式化下单日期
     *
     * @param time
     * @return
     */
    public static String formatOrderTime(String time) {

        long timeLong = parseTime(time, mFormatOrderTimeParse);

        return mFormatOrderTimeFormat.format(new Date(timeLong));
    }

    public static long parseOrderTime(String time) {
        return parseTime(time, mFormatOrderTimeParse);
    }

    public static boolean isToaday(String time) {

        mFormatDay.setLenient(false);
        mFormatDay.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        Date date = null;

        try {
            date = mFormatDay.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (null == date) {
            return false;
        }

        Calendar current = Calendar.getInstance(); // 今天
        Calendar today = Calendar.getInstance(); // 今天

        current.setTime(date);

        if (mFormatDay.format(today.getTime()).equals(mFormatDay.format(current.getTime()))) {
            return true;
        }

        return false;
    }
}
