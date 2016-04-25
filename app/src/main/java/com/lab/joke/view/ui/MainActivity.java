package com.lab.joke.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.util.common.ToastUtil;
import com.lab.joke.view.base.BaseActivity;
import com.lab.joke.view.custom.SRadioButton;
import com.lab.joke.view.ui.tab.TabFriendsFragment;
import com.lab.joke.view.ui.tab.TabMyFragment;
import com.lab.joke.view.ui.tab.TabPublishFragment;
import com.lab.joke.view.ui.tab.TabStoryFragment;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;

/**
 * Created by luokaiwen on 15/4/27.
 * <p/>
 * 主界面
 */
@WLayout(layoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @InjectView(R.id.fl_container)
    FrameLayout flContainer;

    @InjectView(R.id.srb_story)
    SRadioButton srbStory;

    @InjectView(R.id.srb_publish)
    SRadioButton srbPublish;

    @InjectView(R.id.srb_my)
    SRadioButton srbMy;

    @InjectView(R.id.rg_tab)
    RadioGroup rgTab;

    private TabStoryFragment mTabStoryFragment;
    private TabPublishFragment mTabPublishFragment;
    private TabFriendsFragment mTabFriendsFragment;
    private TabMyFragment mTabMyFragment;
    private FragmentManager manager;

    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

//        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        init();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if (System.currentTimeMillis() - mExitTime > 3000) {
                ToastUtil.shortToast(mContext, "再按一次退出程序");
                mExitTime = System.currentTimeMillis();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @OnCheckedChanged(R.id.srb_story)
    void checkStory(CompoundButton button, boolean checked) {

        if (checked) {
            changeFragment(mTabStoryFragment);
        }
    }

    @OnCheckedChanged(R.id.srb_publish)
    void checkPublish(CompoundButton button, boolean checked) {

        if (checked) {
            changeFragment(mTabPublishFragment);
        }
    }

    @OnCheckedChanged(R.id.srb_friends)
    void checkFriends(CompoundButton button, boolean checked) {

        if (checked) {
            changeFragment(mTabFriendsFragment);
        }
    }

    @OnCheckedChanged(R.id.srb_my)
    void checkMy(CompoundButton button, boolean checked) {

        if (checked) {
            changeFragment(mTabMyFragment);
        }
    }

    private void init() {

        manager = getSupportFragmentManager();

        mTabStoryFragment = TabStoryFragment.newInstance();
        mTabPublishFragment = TabPublishFragment.newInstance();
        mTabFriendsFragment = TabFriendsFragment.newInstance();
        mTabMyFragment = TabMyFragment.newInstance();

        findViewById(R.id.srb_story).performClick();
    }

    private void changeFragment(Fragment fragment) {

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_container, fragment);
        transaction.commitAllowingStateLoss();
    }
}
