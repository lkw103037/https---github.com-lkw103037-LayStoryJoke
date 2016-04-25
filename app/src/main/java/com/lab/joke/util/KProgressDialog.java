package com.lab.joke.util;

import android.app.AlertDialog;
import android.content.Context;

public class KProgressDialog extends AlertDialog {
    protected KProgressDialog(Context context) {
        super(context);
    }
//    private ProgressBar mProgressBar;
//    private ImageView mResultImage;
//    private TextView mMessageView;
//
//    private Handler mViewHandler;
//
//    public KProgressDialog(Context context, int theme) {
//        super(context, theme);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mViewHandler = new Handler();
//
//        getWindow().setLayout(250, 250);
//
//        setContentView(R.layout.common_progress);
//        mProgressBar = (ProgressBar) findViewById(R.id.pb_progress);
//    }
//
//    public void setMessage(CharSequence message) {
//        mMessageView.setText(message);
//    }
//
//    public void showProgress() {
//        mProgressBar.setVisibility(View.VISIBLE);
//        mResultImage.setVisibility(View.INVISIBLE);
//    }
//
//    public void setSuccess() {
//        showResult();
//        //mResultImage.setImageResource(R.drawable.common_progress_succ);
//        delayHide();
//    }
//
//    public void setFail() {
//        showResult();
//        //mResultImage.setImageResource(R.drawable.common_progress_fail);
//        delayHide();
//    }
//
//    private void showResult() {
//        mProgressBar.setVisibility(View.INVISIBLE);
//        mResultImage.setVisibility(View.VISIBLE);
//    }
//
//    private void delayHide() {
//        mViewHandler.postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                dismiss();
//            }
//        }, 500);
//    }
}
