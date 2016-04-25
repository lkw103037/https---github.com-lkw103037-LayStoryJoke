package com.lab.joke.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lab.joke.R;
import com.lab.joke.util.common.LogUtil;

/**
 * Created by luokaiwen on 15/7/17.
 * <p/>
 * 进度等待工具类
 */
public class LoadingUtil {

    private static final String TAG = LoadingUtil.class.getSimpleName();
    private static Context mContext;
    private static ProgressLoadingDialog mLoadingDialog;

    public static void show(Context context) {

        if (null == context) {
            return;
        }

        try {

            if (null == mLoadingDialog) {

                mContext = context;
                mLoadingDialog = new ProgressLoadingDialog(mContext, R.style.common_progress_dialog);
            }

            if (!mLoadingDialog.isShowing()) {
                mLoadingDialog.show();
            }

        } catch (Exception e) {

            mLoadingDialog = null;
            mContext = null;
            LogUtil.e(TAG, "show exception");
        }
    }

    public static void hide() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (null != mLoadingDialog && mLoadingDialog.isShowing()) {

                    try {

                        mLoadingDialog.cancel();

                        mLoadingDialog = null;
                        mContext = null;

                    } catch (Exception e) {

                        mLoadingDialog = null;
                        mContext = null;

                        LogUtil.e(TAG, "hide exception");
                    }
                }
            }
        }, 500);
    }

    public static class ProgressLoadingDialog extends AlertDialog {

        public ProgressLoadingDialog(Context context, int theme) {
            super(context, theme);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Window window = getWindow();
            window.setLayout(250, 250);

            WindowManager.LayoutParams params = window.getAttributes();
            params.width = LinearLayout.LayoutParams.MATCH_PARENT;
            params.height = LinearLayout.LayoutParams.MATCH_PARENT;
            mLoadingDialog.getWindow().setAttributes(params);

            mLoadingDialog.setCanceledOnTouchOutside(false);

            //		mWaitingDialog.setCancelable(true);
//		mWaitingDialog.setOnKeyListener(new OnKeyListener()
//		{
//			@Override
//			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
//			{
//				if (keyCode == KeyEvent.KEYCODE_BACK)
//				{
//					if (mCallback != null)
//					{
//						mCallback.backKeyDown();
//					}
//					return true;
//				}
//				return false;
//			}
//		});

            //setContentView(R.layout.common_progress);
        }
    }
}
