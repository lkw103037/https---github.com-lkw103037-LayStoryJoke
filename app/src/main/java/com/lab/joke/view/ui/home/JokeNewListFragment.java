package com.lab.joke.view.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.view.base.BaseFragment;
import com.lab.joke.view.base.WBaseAdapter;

import java.util.ArrayList;

import butterknife.InjectView;

/**
 * Created by rokevin on 16/4/4.
 * <p/>
 * 最新故事列表
 */
@WLayout(layoutId = R.layout.fragment_story_new)
public class JokeNewListFragment extends BaseFragment {

    @InjectView(R.id.lv_story_new)
    ListView lvStoryNew;

    WBaseAdapter mAdapter;

    public static JokeNewListFragment newInstance() {

        JokeNewListFragment newFragment = new JokeNewListFragment();
        Bundle bundle = new Bundle();
        newFragment.setArguments(bundle);

        //bundle还可以在每个标签里传送数据
        return newFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> datas = new ArrayList<>();
        datas.add("1");
        datas.add("1");
        datas.add("1");
        datas.add("1");

        mAdapter = new WBaseAdapter<String>(getActivity(), datas, R.layout.item_story_new) {

            @Override
            public void getItemView(int position, View convertView) {

            }
        };

        lvStoryNew.setAdapter(mAdapter);
    }
}
