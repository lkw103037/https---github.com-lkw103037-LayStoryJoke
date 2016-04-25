package com.lab.joke.view.pop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.util.Util;

/**
 * Created by luokaiwen on 15/5/9.
 * <p/>
 * 弹窗基类
 */
public abstract class BasePop {

    protected final String TAG = getClass().getSimpleName();
    protected Context mContext;
    protected View vAnchorView;
    protected int mWidth = 0;
    protected int mHeight = 0;
    protected PopupWindow mPopupWindow;

    public BasePop(Context context) {
        this.mContext = context;
    }

    /**
     * 判断pop是否显示
     */
    public boolean isShowing() {
        if (null == mPopupWindow) {
            return false;
        }

        return mPopupWindow.isShowing();
    }

    /**
     * 弹出Pop
     */
    public void showPop(View view) {
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                    dismissPop();
                }
            }
        });

        this.vAnchorView = view;
        getPopupWindowInstance();
        configDropAnchor();
    }

    /**
     * 收回Pop
     */
    public void dismissPop() {
        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }

    protected void onCancel() {

    }

    /**
     * 获取PopupWindow实例
     */
    private void getPopupWindowInstance() {
        if (null != mPopupWindow) {
            mPopupWindow.dismiss();
            return;
        } else {
            initPopuptWindow();
        }
    }

    /*
     * 创建PopupWindow
     */
    private void initPopuptWindow() {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        int layoutId = getLayoutId();

//        if (layoutId == WValue.NO_LAYOUT_ID) {
//            new NoConfigLayoutIdException();
//        }

        View popupWindow = layoutInflater.inflate(layoutId, null);
        getPopView(popupWindow);
        mPopupWindow = new PopupWindow(popupWindow, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(false);
        if (mWidth != 0) {
            mPopupWindow.setWidth(Util.dip2px(mContext, mWidth));
        }
        if (mHeight != 0) {
            mPopupWindow.setHeight(Util.dip2px(mContext, mHeight));
        }

        //Drawable drawable = mContext.getResources().getDrawable(R.drawable.bg_transparent);

        //mPopupWindow.setBackgroundDrawable(drawable);

//        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                onCancel();
//            }
//        });
    }

    protected void configDropAnchor() {
        mPopupWindow.showAsDropDown(vAnchorView, 0, 0);
    }

    protected abstract void getPopView(View popupWindow);

    public int getLayoutId() {

        return getClass().getAnnotation(WLayout.class).layoutId();
    }
}
