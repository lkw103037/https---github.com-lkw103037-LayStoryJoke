package com.lab.joke.view.ui.joke;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.environment.AppBundle;
import com.lab.joke.util.common.ToastUtil;
import com.lab.joke.view.base.BaseActivity;
import com.lab.joke.view.custom.TitleView;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by rokevin on 15/9/25.
 * <p/>
 * 发布文本页面
 */
@WLayout(layoutId = R.layout.activity_publish_text)
public class PublishTextActivity extends BaseActivity {

    @InjectView(R.id.tv_title)
    TitleView tvTitle;

    @InjectView(R.id.et_content)
    EditText etContent;

    @InjectView(R.id.btn_add)
    Button btnAdd;

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

                Intent intent = new Intent(mContext, PublishConfirmActivity.class);
                intent.putExtra(AppBundle.TITLE, mBundleTitle);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.btn_add)
    void doAdd() {

        ToastUtil.shortToast(mContext, "添加图片");
    }
}
