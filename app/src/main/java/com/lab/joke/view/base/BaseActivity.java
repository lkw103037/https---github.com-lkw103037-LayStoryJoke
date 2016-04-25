package com.lab.joke.view.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.android.volley.VolleyError;
import com.lab.joke.connection.ActionListener;
import com.lab.joke.connection.WAction;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.controler.Controller;
import com.lab.joke.controler.IActionListener;
import com.lab.joke.data.sp.SPUser;
import com.lab.joke.model.bean.Result;
import com.lab.joke.util.common.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * Created by luokaiwen on 15/4/27.
 * <p/>
 * UI基类
 */
public class BaseActivity extends FragmentActivity implements ActionListener<Result>, IActionListener {

    public static final String TAG = BaseActivity.class.getSimpleName();
    public Context mContext = BaseActivity.this;
    public Controller mController;
    private int contentLayoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //setTranslucentStatus(true);
        }

//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.bg_title);

        beforeContentView();

        int layoutId = getLayoutId();

        if (-1 == layoutId) {
            layoutId = contentLayoutId;
        }
//        if (layoutId == WValue.NO_LAYOUT_ID) {
//            new NoConfigLayoutIdException();
//        }

        if (layoutId != -2) {
            setContentView(getLayoutId());
        }

        mController = new Controller(mContext);
        afterContentView();
    }

    public void beforeContentView() {

    }

    public void afterContentView() {

    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        contentLayoutId = layoutResID;
        ButterKnife.inject(this);
    }

    public int getLayoutId() {

        return getClass().getAnnotation(WLayout.class).layoutId();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 统计页面
        MobclickAgent.onPageStart(TAG);

        // 统计时常
        MobclickAgent.onResume(getApplicationContext());

    }

    @Override
    protected void onPause() {
        super.onPause();

        // 统计页面
        MobclickAgent.onPageEnd(TAG);

        // 统计时常
        MobclickAgent.onPause(getApplicationContext());

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Result response) {

    }

    @Override
    public void onActionSucc(Result result) {

    }

    @Override
    public void onActionFail(Result result) {

        if (null == mContext) {
            return;
        }

        String action = result.getAction();

        if (WAction.LOGIN.equals(action)) {

            // 登录失效，请重新登录
            loginFail();

        } else {
            ToastUtil.shortToast(mContext, result.getState().getStateMessage());
        }
    }

    /**
     * 判断是否登录了
     *
     * @return true：用户已登录 false：用户未登录
     */
    public boolean isLogin() {

        if (!TextUtils.isEmpty(SPUser.getMobilePhone(mContext))) {

            return true;
        }

        return false;
    }

    /**
     * 如果用户没有登录显示用户登录页面
     */
    public void login() {

//        Intent intent = new Intent(mContext, HomeActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(intent);
//
//        startActivity(new Intent(mContext, AccountEntryActivity.class));
//
//        EventBus.getDefault().post(new LoginEvent());
    }

    /**
     * 登录失败处理
     */
    public void loginFail() {
        // 登录失效，请重新登录
//        SPUser.clear(mContext);
//        login();
//        ToastUtil.shortToast(mContext, R.string.toast_username_or_password_is_wrong);
    }

    /**
     * 跳转至首页
     */
    public void goHome() {

//        Intent homeIntent = new Intent(mContext, HomeActivity.class);
//        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(homeIntent);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
