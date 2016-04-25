package com.lab.joke.util;

import java.text.DecimalFormat;

/**
 * 价格处理类
 */
public class PriceUtil {
    private static DecimalFormat format = new DecimalFormat("#0.00");
    private static DecimalFormat format2 = new DecimalFormat("#0");

    public static String formatPriceInt(float price) {
        return format2.format(price) + "";
    }

    public static String formatPriceInt(double price) {
        if (price < 0) {
            price = 0;
        }
        return format2.format(price) + "";
    }

    public static String formatPrice(float price) {
        return format.format(price) + "";
    }

    public static String formatPrice(double price) {

        return format.format(price) + "";
    }

    public static String symbolPrice(int price) {

        return "￥" + format.format(price);
    }

    public static String symbolPrice(float price) {

        return "￥" + format.format(price);
    }

    public static String symbolPrice(String price) {

        return "￥" + price;
    }

    public static String symbolPriceNoDecimal(float price) {
        return "￥" + format2.format(price);
    }

    public static String symbolPriceNoDecimal(double price) {
        return "￥" + format2.format(price);
    }

    public static String symbolPrice(double price) {

        return "￥" + format.format(price);
    }

    /**
     * 获取元
     *
     * @param price
     * @return
     */
    public static String getYuan(String price) {
        float priceFloat = 0;

        try {
            priceFloat = Float.parseFloat(price);
        } catch (Exception e) {
            priceFloat = 0;
        }
        return format.format(priceFloat) + "元";
    }

    /**
     * 获取元
     *
     * @param price
     * @return
     */
    public static String getYuan(double price) {
        return format.format(price) + "元";
    }

    /**
     * 获取资金账户
     *
     * @param account
     * @return
     */
    public static String getUserIdByFundsAccount(String account) {
        String sub = account.substring(1);

        long userIdL = Long.parseLong(sub);

        return userIdL + "";
    }
}
