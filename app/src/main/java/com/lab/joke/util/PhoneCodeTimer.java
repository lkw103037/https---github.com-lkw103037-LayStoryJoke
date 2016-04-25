package com.lab.joke.util;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.lab.joke.R;

/**
 * 短信验证倒计时定时器
 */
public class PhoneCodeTimer extends CountDownTimer {
    private final TextView mTextView;
    private boolean setBackground = false;

    /**
     * 执行倒计时
     *
     * @param textView 倒计时控件
     */
    public PhoneCodeTimer(TextView textView) {
        super(60000, 1000);
        this.mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        int countDown = (int) (millisUntilFinished / 1000);
        if (mTextView.isClickable()) {
            mTextView.setClickable(false);
        }
        if (!setBackground) {
            mTextView.setTextColor(mTextView.getContext().getResources().getColor(R.color.gray));
            //mTextView.setBackgroundResource(R.drawable.btn_ring_gray);
            setBackground = true;
        }
        mTextView.setText("重新下发" + countDown + "s");
    }

    @Override
    public void onFinish() {

        setBackground = false;
        mTextView.setClickable(true);
        mTextView.setText("重新下发");
        mTextView.setTextColor(mTextView.getContext().getResources().getColor(R.color.white));
        //mTextView.setBackgroundResource(R.drawable.btn_solid_corner_red);
        cancel();
    }
}
