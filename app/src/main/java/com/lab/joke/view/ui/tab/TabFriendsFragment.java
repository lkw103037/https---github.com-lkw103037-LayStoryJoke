package com.lab.joke.view.ui.tab;

import android.os.Bundle;
import android.view.View;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.view.base.BaseFragment;
import com.lab.joke.view.custom.TitleView;

import butterknife.InjectView;

/**
 * Created by luokaiwen on 15/5/14.
 * <p/>
 * 朋友碎片
 */
@WLayout(layoutId = R.layout.fragment_tab_friends)
public class TabFriendsFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TitleView tvTitle;

    public static TabFriendsFragment newInstance() {

        TabFriendsFragment tabPublishFragment = new TabFriendsFragment();

        Bundle bundle = new Bundle();
        tabPublishFragment.setArguments(bundle);

        return tabPublishFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (null == getActivity()) {
            return;
        }
    }
}
