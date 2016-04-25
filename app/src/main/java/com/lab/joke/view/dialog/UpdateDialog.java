package com.lab.joke.view.dialog;

import android.content.Context;
import android.view.View;

/**
 * Created by luokaiwen on 15/5/28.
 * <p/>
 * 更新弹框
 */
public class UpdateDialog extends BaseDialog {
    /**
     * 构造函数
     *
     * @param context
     */
    protected UpdateDialog(Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void showView(View view) {

    }

    //private VersionData mVersion;

//    TextView tvContent;
//    Button btnUpdate;
//
//    /**
//     * 构造函数
//     *
//     * @param context
//     */
//    public UpdateDialog(Context context) {
//        super(context);
//    }
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.dialog_update;
//    }
//
//    @Override
//    protected void showView(View view) {
//
//        tvContent = (TextView) view.findViewById(R.id.tv_content);
//        btnUpdate = (Button) view.findViewById(R.id.btn_update);
//
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if (null != mOnUpdateListener) {
//                    mOnUpdateListener.onUpdateConfirm();
//                }
//                cancel();
//            }
//        });
//
//        if (null != mVersion) {
//
//            tvContent.setText(Html.fromHtml(mVersion.getContent()));
//        }
//    }
//
//    public void showDialog(ViewGroup viewGroup, VersionData version) {
//
//        mVersion = version;
//
//        try {
//            if (!isShowing()) {
//                super.showDialog(viewGroup);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private OnUpdateListener mOnUpdateListener;
//
//    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
//        mOnUpdateListener = onUpdateListener;
//    }
//
//    public interface OnUpdateListener {
//
//        void onUpdateConfirm();
//    }
}
