package com.lab.joke.view.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lab.joke.R;
import com.lab.joke.util.common.ToastUtil;

/**
 * Created by rokevin on 16/4/2.
 */
public class UserItemView extends LinearLayout implements View.OnClickListener {

    Context mContext;

    /**
     * 我的页面用户Item组件数
     */
    private TextView tvCount;

    /**
     * 我的页面用户Item组件名称
     */
    private TextView tvName;

    public UserItemView(Context context) {
        super(context);

        mContext = context;
    }

    public UserItemView(Context context, AttributeSet attrs) {

        super(context, attrs);

        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.custom_user_item, this);

        tvCount = (TextView) view.findViewById(R.id.tv_count);
        tvName = (TextView) view.findViewById(R.id.tv_name);
    }

    public void setCount(String count) {

        count = TextUtils.isEmpty(count) ? "" : count;

        tvCount.setText(count);
    }

    public void setName(String name) {

        name = TextUtils.isEmpty(name) ? "" : name;

        tvName.setText(name);
    }

    @Override
    public void onClick(View v) {

        ToastUtil.shortToast(mContext, "你好");
    }
}
