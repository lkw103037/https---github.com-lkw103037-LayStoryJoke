package com.lab.joke.view.ui.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.CompoundButton;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.view.base.BaseFragment;
import com.lab.joke.view.custom.SRadioButton;
import com.lab.joke.view.ui.home.JokeHotListFragment;
import com.lab.joke.view.ui.home.JokeNewListFragment;
import com.lab.joke.view.ui.home.JokePagerAdapter;

import java.util.ArrayList;

import butterknife.InjectView;
import butterknife.OnCheckedChanged;

/**
 * Created by luokaiwen on 15/5/14.
 * <p/>
 * 故事碎片
 */
@WLayout(layoutId = R.layout.fragment_tab_story)
public class TabStoryFragment extends BaseFragment {

    @InjectView(R.id.srb_new)
    SRadioButton srbNew;

    @InjectView(R.id.srb_hot)
    SRadioButton srbHot;

    @InjectView(R.id.vp_story)
    ViewPager vpStory;

    JokePagerAdapter mAdapter;

    public static TabStoryFragment newInstance() {

        TabStoryFragment tabStoryFragment = new TabStoryFragment();

        // Fragment传参可以在此处理
        Bundle bundle = new Bundle();
        tabStoryFragment.setArguments(bundle);

        return tabStoryFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        srbNew.setChecked(true);

        ArrayList fragmentList = new ArrayList<Fragment>();
        Fragment newListFragment = JokeNewListFragment.newInstance();
        Fragment hotListFragment = JokeHotListFragment.newInstance();
        fragmentList.add(newListFragment);
        fragmentList.add(hotListFragment);

        //给ViewPager设置适配器
        JokePagerAdapter pagerAdapter = new JokePagerAdapter(getChildFragmentManager(), fragmentList);

        vpStory.setAdapter(pagerAdapter);
        vpStory.setCurrentItem(0);//设置当前显示标签页为第一页
        vpStory.addOnPageChangeListener(new StoryOnPageChangeListener());//页面变化时的监听器
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnCheckedChanged(R.id.srb_new)
    void checkPublish(CompoundButton button, boolean checked) {

        if (checked) {
            vpStory.setCurrentItem(0);
        }
    }

    @OnCheckedChanged(R.id.srb_hot)
    void checkFriends(CompoundButton button, boolean checked) {

        if (checked) {
            vpStory.setCurrentItem(1);
        }
    }

    public class StoryOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            if (position == 0) {
                srbNew.setChecked(true);
            } else if (position == 1) {
                srbHot.setChecked(true);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        LogUtil.e(TAG, "onDetach()");
//        try {
//            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
//            childFragmentManager.setAccessible(true);
//            childFragmentManager.set(this, null);
//
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
