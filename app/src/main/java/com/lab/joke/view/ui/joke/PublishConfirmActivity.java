package com.lab.joke.view.ui.joke;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.environment.AppBundle;
import com.lab.joke.util.common.ToastUtil;
import com.lab.joke.view.base.BaseActivity;
import com.lab.joke.view.custom.TitleView;

import butterknife.InjectView;

/**
 * Created by rokevin on 15/9/25.
 * <p/>
 * 发布确认页面
 */
@WLayout(layoutId = R.layout.activity_publish_confirm)
public class PublishConfirmActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TitleView tvTitle;

    @InjectView(R.id.iv_play)
    ImageView ivPlay;

    @InjectView(R.id.tv_time)
    TextView tvTime;

    private String mBundleTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBundleTitle = getIntent().getStringExtra(AppBundle.TITLE);

        if (TextUtils.isEmpty(mBundleTitle)) {
            mBundleTitle = "";
        }

        tvTitle.setTitle(mBundleTitle);

        tvTitle.setOnFunctionClickListener(new TitleView.OnFunctionClickListener() {
            @Override
            public void onFunctionClick() {

                ToastUtil.shortToast(mContext, "发布成功");
            }
        });

        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ToastUtil.shortToast(mContext, "开始播放");
            }
        });

        tvTime.setText("试听 00:00/00:32");
    }


}
