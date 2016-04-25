package com.lab.joke.view.ui.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by rokevin on 16/4/4.
 */
public class JokePagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments;

    public JokePagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
        if (fragments == null) {
            this.fragments = new ArrayList<>();
        }
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragments.get(arg0);
    }
}
