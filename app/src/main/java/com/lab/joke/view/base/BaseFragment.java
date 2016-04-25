package com.lab.joke.view.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
 * Created by luokaiwen on 15/4/29.
 * <p/>
 * 碎片基类
 */
public class BaseFragment extends Fragment implements ActionListener<Result>, IActionListener {

    protected String TAG = getClass().getSimpleName();
    public Controller mController;
    protected View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        int layoutId = getLayoutId();

//        if (layoutId == WValue.NO_LAYOUT_ID) {
//
//            new NoConfigLayoutIdException();
//        }

        rootView = inflater.inflate(layoutId, container, false);

        ButterKnife.inject(this, rootView);
        mController = new Controller(getActivity());

        return rootView;
    }

    @Override
    public void onResume() {

        // 统计页面
        MobclickAgent.onPageStart(TAG);

        super.onResume();
    }

    @Override
    public void onPause() {

        // 统计页面
        MobclickAgent.onPageEnd(TAG);

        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (null != rootView && null != rootView.getParent()) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        ButterKnife.reset(this);
    }

    public int getLayoutId() {

        return getClass().getAnnotation(WLayout.class).layoutId();
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

        if (null == getActivity()) {
            return;
        }

        String action = result.getAction();

        if (WAction.LOGIN.equals(action)) {

            // 登录失效，请重新登录
            loginFail();
        } else {
            ToastUtil.shortToast(getActivity(), result.getState().getStateMessage());
        }
    }

    /**
     * 判断是否登录了
     *
     * @return true：用户已登录 false：用户未登录
     */
    public boolean isLogin() {

        if (!TextUtils.isEmpty(SPUser.getMobilePhone(getActivity()))) {

            return true;
        }

        return false;
    }

    /**
     * 如果用户没有登录显示用户登录页面
     */
    public void login() {

    }

    /**
     * 登录失败处理
     */
    public void loginFail() {

    }
}
