package com.lab.joke.view.ui.tab;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lab.joke.R;
import com.lab.joke.connection.annotation.WLayout;
import com.lab.joke.environment.AppBundle;
import com.lab.joke.environment.BizzType;
import com.lab.joke.util.common.ToastUtil;
import com.lab.joke.view.base.BaseFragment;
import com.lab.joke.view.custom.TitleView;
import com.lab.joke.view.ui.joke.PublishRecordActivity;
import com.lab.joke.view.ui.joke.PublishSpeechActivity;
import com.lab.joke.view.ui.joke.PublishTextActivity;

import butterknife.InjectView;
import butterknife.OnClick;

/**
 * Created by luokaiwen on 15/5/14.
 * <p/>
 * 发布碎片
 */
@WLayout(layoutId = R.layout.fragment_tab_publish)
public class TabPublishFragment extends BaseFragment {

    @InjectView(R.id.tv_title)
    TitleView tvTitle;

    @InjectView(R.id.et_story_title)
    EditText etStoryTitle;

    @InjectView(R.id.iv_record)
    ImageView ivRecord;

    @InjectView(R.id.tv_record)
    TextView tvRecord;

    @InjectView(R.id.iv_speech)
    ImageView ivSpeech;

    @InjectView(R.id.tv_speech)
    TextView tvSpeech;

    @InjectView(R.id.iv_text)
    ImageView ivText;

    @InjectView(R.id.tv_text)
    TextView tvText;

    private String mStoryType = BizzType.Publish.RECORD;

    public static TabPublishFragment newInstance() {

        TabPublishFragment tabPublishFragment = new TabPublishFragment();

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

        tvTitle.setOnFunctionClickListener(new TitleView.OnFunctionClickListener() {
            @Override
            public void onFunctionClick() {

                String trim = etStoryTitle.getText().toString().trim();

                if (TextUtils.isEmpty(trim)) {
                    ToastUtil.shortToast(getActivity(), R.string.toast_please_input_story_title);
                    return;
                }

                Intent intent = new Intent();

                switch (mStoryType) {

                    case BizzType.Publish.RECORD:

                        intent.setClass(getActivity(), PublishRecordActivity.class);
                        break;

                    case BizzType.Publish.SPEECH:

                        intent.setClass(getActivity(), PublishSpeechActivity.class);
                        break;

                    case BizzType.Publish.TEXT:

                        intent.setClass(getActivity(), PublishTextActivity.class);
                        break;
                }

                intent.putExtra(AppBundle.TITLE, trim);
                startActivity(intent);
            }
        });

        doRecord();
    }

    @OnClick(R.id.ll_record)
    void doRecord() {

        mStoryType = BizzType.Publish.RECORD;

        ivRecord.setImageResource(R.mipmap.ic_launcher);
        tvRecord.setTextColor(getResources().getColor(R.color.red));

        ivSpeech.setImageResource(R.mipmap.ic_launcher);
        tvSpeech.setTextColor(getResources().getColor(R.color.gray_dark));

        ivText.setImageResource(R.mipmap.ic_launcher);
        tvText.setTextColor(getResources().getColor(R.color.gray_dark));
    }

    @OnClick(R.id.ll_speech)
    void doSpeech() {

        mStoryType = BizzType.Publish.SPEECH;

        ivRecord.setImageResource(R.mipmap.ic_launcher);
        tvRecord.setTextColor(getResources().getColor(R.color.gray_dark));

        ivSpeech.setImageResource(R.mipmap.ic_launcher);
        tvSpeech.setTextColor(getResources().getColor(R.color.red));

        ivText.setImageResource(R.mipmap.ic_launcher);
        tvText.setTextColor(getResources().getColor(R.color.gray_dark));
    }

    @OnClick(R.id.ll_text)
    void doText() {

        mStoryType = BizzType.Publish.TEXT;

        ivRecord.setImageResource(R.mipmap.ic_launcher);
        tvRecord.setTextColor(getResources().getColor(R.color.gray_dark));

        ivSpeech.setImageResource(R.mipmap.ic_launcher);
        tvSpeech.setTextColor(getResources().getColor(R.color.gray_dark));

        ivText.setImageResource(R.mipmap.ic_launcher);
        tvText.setTextColor(getResources().getColor(R.color.red));
    }
}
