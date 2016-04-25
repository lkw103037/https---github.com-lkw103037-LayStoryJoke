package com.lab.joke.view.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.lab.joke.R;
import com.lab.joke.util.common.ScreenUtil;

/**
 * Created by luokaiwen on 15/5/9.
 * <p/>
 * 自定义RadioButton图片位置的单选按钮
 */
public class SRadioButton extends RadioButton {

    private Drawable mRadioDrawable;

    public SRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SRadioButton(Context context) {
        super(context);
    }

    public SRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.tabImg);
        mRadioDrawable = typedArray.getDrawable(R.styleable.tabImg_img);

        if (null != mRadioDrawable) {
            int length = ScreenUtil.dip2px(context, 20);
            mRadioDrawable.setBounds(0, 0, length, length);
            setCompoundDrawables(null, mRadioDrawable, null, null);
        }

        typedArray.recycle();
    }
}
