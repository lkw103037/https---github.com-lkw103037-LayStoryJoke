package com.lab.joke.view.dialog;

import android.content.Context;
import android.view.View;

/**
 * Created by luokaiwen on 15/5/28.
 * <p/>
 * 强制更新弹框
 */
public class UpdateForceDialog extends BaseDialog {
    /**
     * 构造函数
     *
     * @param context
     */
    protected UpdateForceDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void showView(View view) {

    }

//    NumberProgressBar npbProgress;
//    Button btnUpdate;
//
//    /**
//     * 构造函数
//     *
//     * @param context
//     */
//    public UpdateForceDialog(Context context) {
//        super(context);
//    }
//
//    @Override
//    protected int getLayoutId() {
//
//        return R.layout.dialog_update_force;
//    }
//
//    @Override
//    protected void showView(View view) {
//
//        npbProgress = (NumberProgressBar) view.findViewById(R.id.npb_progress);
//        btnUpdate = (Button) view.findViewById(R.id.btn_update);
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (((Button) v).getText().equals("安装")) {
//                    if (null != mOnUpdateForceListener) {
//                        mOnUpdateForceListener.onInstall();
//                    }
//                } else {
//                    if (null != mOnUpdateForceListener) {
//                        mOnUpdateForceListener.onUpdateForce();
//                        npbProgress.setVisibility(View.VISIBLE);
//                        btnUpdate.setEnabled(false);
//                    }
//                }
//            }
//        });
//    }
//
//    public void setProgress(int progress) {
//
//        npbProgress.setProgress(progress);
//        if (progress >= 99) {
//            npbProgress.setProgress(100);
//            btnUpdate.setText("安装");
//            btnUpdate.setEnabled(true);
//        }
//    }
//
//    public NumberProgressBar getProgress() {
//
//        return npbProgress;
//    }
//
//    @Override
//    public boolean isOutSideTouch() {
//        return false;
//    }
//
//    @Override
//    public boolean isDisableBack() {
//        return true;
//    }
//
//    private OnUpdateForceListener mOnUpdateForceListener;
//
//    public void setOnUpdateForceListener(OnUpdateForceListener onUpdateForceListener) {
//        mOnUpdateForceListener = onUpdateForceListener;
//    }
//
//    public interface OnUpdateForceListener {
//
//        public void onUpdateForce();
//
//        public void onInstall();
//    }
}
