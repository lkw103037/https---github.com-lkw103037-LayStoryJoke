package com.lab.joke.util;

public class ProgressBarUtil {
//    private static Context mContext;
//    private static KProgressDialog mWaitingDialog;
//    private static ProgressDialogCallback mCallback;
//
//    private static void initWaitingDialog(Context context) {
//        mContext = context;
//
//        mWaitingDialog = new KProgressDialog(mContext, R.style.common_progress_dialog);
//
//        LayoutParams params = mWaitingDialog.getWindow().getAttributes();
//        params.width = LayoutParams.MATCH_PARENT;
//        params.height = LayoutParams.MATCH_PARENT;
//        mWaitingDialog.getWindow().setAttributes(params);
//
//        mWaitingDialog.setCanceledOnTouchOutside(false);
////		mWaitingDialog.setCancelable(true);
////		mWaitingDialog.setOnKeyListener(new OnKeyListener()
////		{
////			@Override
////			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
////			{
////				if (keyCode == KeyEvent.KEYCODE_BACK)
////				{
////					if (mCallback != null)
////					{
////						mCallback.backKeyDown();
////					}
////					return true;
////				}
////				return false;
////			}
////		});
//    }
//
//    public static void showWaitingDialog(Context context) {
//        if (null == context) {
//            if (null != mContext) {
//                hideWaitingDialog();
//            }
//            return;
//        } else {
//            if (null != mContext) {
//                hideWaitingDialog();
//            }
//
//            initWaitingDialog(context);
//            mWaitingDialog.show();
//            mWaitingDialog.showProgress();
//            // mWaitingDialog.setMessage("操作中...");
//        }
//    }
//
//    public static void showWaitingDialog(Context context, String msg) {
//        initWaitingDialog(context);
//
//        mWaitingDialog.show();
//        mWaitingDialog.showProgress();
//        mWaitingDialog.setMessage(msg);
//    }
//
//    public static void showWaitingDialog(Context context, ProgressDialogCallback callback) {
//        showWaitingDialog(context);
//        mCallback = callback;
//    }
//
//    public static void showWaitingDialog(Context context, String msg, ProgressDialogCallback callback) {
//        showWaitingDialog(context, msg);
//        mCallback = callback;
//    }
//
//    public static void hideWaitingDialog() {
//        if (mWaitingDialog != null) {
//            try {
//                mWaitingDialog.cancel();
//                mContext = null;
//            } catch (Exception e) {
//                mContext = null;
//            }
//        }
//        mCallback = null;
//    }
//
//    public static void hideWaitingDialog(boolean success) {
//        if (mWaitingDialog != null) {
//            if (success)
//                mWaitingDialog.setSuccess();
//            else
//                mWaitingDialog.setFail();
//        }
//        mCallback = null;
//    }
//
//    public interface ProgressDialogCallback {
//        public void backKeyDown();
//    }
}
