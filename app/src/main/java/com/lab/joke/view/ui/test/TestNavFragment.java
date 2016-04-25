package com.lab.joke.view.ui.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lab.joke.view.base.BaseFragment;
import com.lab.joke.view.base.WBaseAdapter;

import java.util.ArrayList;

/**
 * Created by luokaiwen on 15/5/4.
 * <p/>
 * 测试页面
 */
//@WLayout(layoutId = R.layout.test_fragment_nav_test)
public class TestNavFragment extends BaseFragment {

//    @InjectView(R.id.lv_test)
//    ListView lvTest;

    private WBaseAdapter<DemoActivity> mAdapter;
    private ArrayList<DemoActivity> activitiesList = new ArrayList<DemoActivity>();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

//        addActivity(TestBusinessActivity.class, "业务测试页面");
//        addActivity(TestWxPayActivity.class, "微信支付测试页面");
//        addActivity(TestShareActivity.class, "分享测试页面");
//        addActivity(TestAlipayActivity.class, "支付包测试页面");
//        addActivity(TestMapActivity.class, "百度地图测试页面");
//        addActivity(TestJPushActivity.class, "JPush测试页面");
        initActivities();

        super.onViewCreated(view, savedInstanceState);
    }

    private void addActivity(Class<?> activity, String name) {
        DemoActivity demo = new DemoActivity(activity, name);
        activitiesList.add(demo);
    }

    private void initActivities() {

//        mAdapter = new WBaseAdapter<DemoActivity>(getActivity(), activitiesList, R.layout.item_test) {
//            @Override
//            public void getItemView(int position, View convertView) {
//                DemoActivity activity = mAdapter.getList().get(position);
//                //TextView tvName = ViewHolder.get(convertView, R.id.tv_name);
//                //tvName.setText(activity.getName());
//            }
//        };
//        lvTest.setAdapter(mAdapter);
//        lvTest.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                DemoActivity activity = mAdapter.getList().get(position);
//                startActivity(new Intent(getActivity(), activity.getActivity()));
//            }
//        });
    }

    private void addActivity(DemoActivity demo) {
        activitiesList.add(demo);
    }

    private void addActivity(Class<?> activity) {
        DemoActivity demo = new DemoActivity(activity, activity.getSimpleName());
        activitiesList.add(demo);
    }

    private class DemoActivity {
        private Class<?> activity;
        private String name;


        private DemoActivity(Class<?> activity, String name) {
            this.activity = activity;
            this.name = name;
        }

        public Class<?> getActivity() {
            return activity;
        }

        public void setActivity(Class<?> activity) {
            this.activity = activity;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "DemoActivity{" +
                    "activity=" + activity +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
