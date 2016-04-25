package com.lab.joke.view.ui.tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.view.base.BaseFragment;
import com.lab.joke.view.custom.UserItemView;
import com.lab.joke.view.ui.user.MessageActivity;
import com.lab.joke.view.ui.user.SettingActivity;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by luokaiwen on 15/5/14.
 * <p/>
 * 故事碎片
 */
@WLayout(layoutId = R.layout.fragment_tab_my)
public class TabMyFragment extends BaseFragment {

    @InjectView(R.id.c_publish)
    UserItemView cPublish;

    @InjectView(R.id.c_response)
    UserItemView cResponse;

    @InjectView(R.id.c_fav)
    UserItemView cFav;

    @InjectView(R.id.c_follow)
    UserItemView cFollow;

    @InjectView(R.id.c_fans)
    UserItemView cFans;

    @InjectView(R.id.c_message)
    UserItemView cMessage;

    @InjectView(R.id.c_recently)
    UserItemView cRecently;

    public static TabMyFragment newInstance() {

        TabMyFragment tabMyFragment = new TabMyFragment();

        Bundle bundle = new Bundle();
        tabMyFragment.setArguments(bundle);

        return tabMyFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (null == getActivity()) {
            return;
        }

        cPublish.setCount("1");
        cPublish.setName("发表");

        cResponse.setCount("2");
        cResponse.setName("回应");

        cFav.setCount("3");
        cFav.setName("喜欢");

        cFollow.setCount("4");
        cFollow.setName("关注");

        cFans.setCount("5");
        cFans.setName("粉丝");

        cMessage.setCount("6");
        cMessage.setName("消息");

        cRecently.setCount("7");
        cRecently.setName("最近看过");

    }

    /**
     * 跳转到设置页面
     */
    @OnClick(R.id.btn_setting)
    void doSetting() {
        startActivity(new Intent(getActivity(), SettingActivity.class));
    }

    /**
     * 跳转到消息页面
     */
    @OnClick(R.id.btn_message)
    void doMessage() {
        startActivity(new Intent(getActivity(), MessageActivity.class));
    }
}
