package com.lab.joke.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lab.joke.util.common.ScreenUtil;
import com.lab.joke.environment.Constant;

import java.io.File;

/**
 * Created by luokaiwen on 15/8/24.
 * <p/>
 * 图片处理类
 */
public class WWImageUtil {

    /**
     * 设置图片，如果图片地址无效则设置随机颜色的展位图
     *
     * @param simpleDraweeView 显示图片组件
     * @param url              图片地址
     */
    public static void setImage(SimpleDraweeView simpleDraweeView, String url) {

        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();

        Context context = simpleDraweeView.getContext();

        int colorId = getRandomColor(context);
        hierarchy.setPlaceholderImage(colorId);
        hierarchy.setFadeDuration(300);

        simpleDraweeView.setImageURI(Uri.parse(Constant.IMAGE_URL + url));
    }

    /**
     * 设置头像，如果图片地址无效则设置随机颜色的背景图
     * 1.没有设置头像的用随机背景色加用户名首字符当做头像
     * 2.如果特殊情况用户名为空时则不添加字符在随机背景色上
     * 3.如果设置了头像则正常显示
     *
     * @param simpleDraweeView 显示图片组件
     * @param url              图片地址
     */
    public static void setAvatar(final SimpleDraweeView simpleDraweeView, String url, final String userName) {

        GenericDraweeHierarchy hierarchy = simpleDraweeView.getHierarchy();

        hierarchy.setFadeDuration(300);

        final Context context = simpleDraweeView.getContext();

        final int colorId = getRandomColor(context);

        if (TextUtils.isEmpty(url) && !TextUtils.isEmpty(userName)) {

            // 创建头像Drawable
            Drawable drawable = new Drawable() {

                private Paint mPaint;
                private int x;
                private int y;

                @Override
                public void draw(Canvas canvas) {

                    mPaint = new Paint();
                    mPaint.setAntiAlias(true);
                    x = simpleDraweeView.getWidth() / 2;
                    y = simpleDraweeView.getHeight() / 2;

                    Paint circlePaint = new Paint();
                    circlePaint.setAntiAlias(true);
                    circlePaint.setColor(context.getResources().getColor(getRandomColor(context)));

                    canvas.drawCircle(x, y, x, circlePaint);
                    //canvas.drawColor(mContext.getResources().getColor(getRandomColor(mContext)));

                    // url为空则头像使用用户名的头一个字母放在随机背景色中，如果用户名为空则不画字符到随机背景色上
                    if (!TextUtils.isEmpty(userName)) {

                        Paint paint = new Paint();
                        paint.setAntiAlias(true);
                        paint.setColor(Color.WHITE);
                        int textSize = ScreenUtil.dip2px(context, 30);
                        paint.setTextSize(textSize);
                        paint.setStrokeWidth(ScreenUtil.dip2px(context, 1));
                        paint.setTextAlign(Paint.Align.CENTER);
                        canvas.drawText(userName.substring(0, 1), x, y + (int) (textSize * 0.4), paint);
                    }
                }

                @Override
                public void setAlpha(int alpha) {

                }

                @Override
                public void setColorFilter(ColorFilter cf) {

                }

                @Override
                public int getOpacity() {
                    return PixelFormat.TRANSLUCENT;
                }
            };

            // 设置drawable后得重新设置头像圆形的属性
            hierarchy.setRoundingParams(hierarchy.getRoundingParams().setRoundAsCircle(true));

            // 随机颜色加名字首字符当占位图
            hierarchy.setPlaceholderImage(drawable);

        } else {

            // 随机颜色当占位图
            hierarchy.setPlaceholderImage(colorId);
        }

        // 设置过头像的正常请求
        simpleDraweeView.setImageURI(Uri.parse(Constant.IMAGE_URL + url));
    }

    /**
     * 获取随机颜色
     *
     * @param context
     * @return
     */
    public static int getRandomColor(Context context) {

        int random = (int) (Math.random() * 9);

        int colorId = context.getResources().getIdentifier("random_" + random, "color", context.getPackageName());

        return colorId;
    }

    /**
     * 获取图片文件的Uri
     *
     * @param file 文件
     * @return
     */
    public static Uri getPicUri(File file) {

        return Uri.fromFile(file);
    }

    /**
     * 解析图片地址
     *
     * @param url 图片地址
     * @return
     */
    public static Uri parseUrl(Context context, String url) {

        return Uri.parse(Constant.IMAGE_URL + url);
    }
}
