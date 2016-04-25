package com.lab.joke.view.custom;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lab.joke.R;

/**
 * Created by rokevin on 15/10/2.
 * <p/>
 * 标题自定义类
 */
public class TitleView extends LinearLayout {

    private ImageView ivBack;
    private FrameLayout flFunction;
    private TextView tvTitleName;
    private Button btnFunction;

    private Drawable mLeftDrawable;
    private String mTitle;
    private String mFunctionText;

    private OnFunctionClickListener mOnFunctionClickListener;
    private OnTitleLeftClickListener mOnTitleLeftClickListener;

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);

        View mRootView = LayoutInflater.from(context).inflate(R.layout.custom_title, this);
        ivBack = (ImageView) mRootView.findViewById(R.id.iv_back);
        tvTitleName = (TextView) mRootView.findViewById(R.id.tv_title_name);
        flFunction = (FrameLayout) mRootView.findViewById(R.id.fl_function);
        btnFunction = (Button) mRootView.findViewById(R.id.btn_function);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.titleView);
        mLeftDrawable = typedArray.getDrawable(R.styleable.titleView_leftImg);
        mTitle = typedArray.getString(R.styleable.titleView_titleText);
        mFunctionText = typedArray.getString(R.styleable.titleView_functionText);

        if (null != mLeftDrawable) {

            ivBack.setImageDrawable(mLeftDrawable);
        } else {

            ivBack.setImageResource(R.mipmap.bar_back);
        }

        if (TextUtils.isEmpty(mTitle)) {
            mTitle = "";
        }

        tvTitleName.setText(mTitle);

        if (TextUtils.isEmpty(mFunctionText)) {
            mFunctionText = "";
            btnFunction.setVisibility(View.GONE);
        } else {
            btnFunction.setText(mFunctionText);
            btnFunction.setVisibility(View.VISIBLE);
        }

        ivBack.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // 如果设置了标题左边按钮坚挺则执行左边按钮功能
                if (null != mOnTitleLeftClickListener) {
                    mOnTitleLeftClickListener.onTitleLeftClick();
                } else {
                    ((Activity) getContext()).finish();
                }
            }
        });

        btnFunction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null != mOnFunctionClickListener) {

                    mOnFunctionClickListener.onFunctionClick();
                }
            }
        });

        typedArray.recycle();
    }

    public void setOnFunctionClickListener(OnFunctionClickListener onFunctionClickListener) {

        mOnFunctionClickListener = onFunctionClickListener;
    }

    public void setOnTitleLeftClickListener(OnTitleLeftClickListener onTitleLeftClickListener) {

        mOnTitleLeftClickListener = onTitleLeftClickListener;
    }

    public interface OnFunctionClickListener {

        void onFunctionClick();
    }

    public interface OnTitleLeftClickListener {

        void onTitleLeftClick();
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {

        tvTitleName.setText(title);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(int title) {

        tvTitleName.setText(title);
    }

    /**
     * 设置左边的图片
     *
     * @param resId 资源ID
     */
    public void setmLeftImage(int resId) {

        ivBack.setImageResource(resId);
    }

    /**
     * 设置功能键按钮名称
     *
     * @param text
     */
    public void setFunctionText(String text) {

        if (!TextUtils.isEmpty(text)) {
            btnFunction.setVisibility(View.VISIBLE);
            btnFunction.setText(text);
        } else {
            btnFunction.setVisibility(View.GONE);
            btnFunction.setText(text);
        }
    }

    /**
     * 设置功能键按钮名称
     *
     * @param textId
     */
    public void setFunctionText(int textId) {

        btnFunction.setVisibility(View.VISIBLE);
        btnFunction.setText(textId);
    }

    /**
     * 获取功能按钮名称
     *
     * @return
     */
    public String getFunctionText() {

        return btnFunction.getText().toString();
    }

    /**
     * 隐藏功能键
     */
    public void hideFunction() {
        flFunction.setVisibility(View.INVISIBLE);
    }

    /**
     * 显示功能键
     */
    public void showFunction() {
        flFunction.setVisibility(View.VISIBLE);
    }

}
