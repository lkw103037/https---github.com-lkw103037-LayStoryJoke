package com.lab.joke.service;

public class UpdateManager {

//    private Context mContext;
//    private ViewGroup mViewGroup;
//
//    //提示语
//    private String updateMsg = "有最新的软件包哦，亲快下载吧~";
//
//    //返回的安装包url
//    private String apkUrl = "";
//
//    private Dialog noticeDialog;
//
//    private Dialog downloadDialog;
//    /* 下载包安装路径 */
//    private static final String savePath = Environment.getExternalStorageDirectory() + "/wonderworld";
//
//    private static final String saveFileName = savePath + "UpdateDemoRelease.apk";
//
//    /* 进度条与通知ui刷新的handler和msg常量 */
//    private NumberProgressBar mProgress;
//
//
//    private static final int DOWN_UPDATE = 1;
//
//    private static final int DOWN_OVER = 2;
//
//    private int progress;
//
//    private Thread downLoadThread;
//
//    private boolean interceptFlag = false;
//
//    UpdateForceDialog updateForceDialog;
//
//    private Handler mHandler = new Handler() {
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case DOWN_UPDATE:
//
//                    updateForceDialog.setProgress(progress);
//                    //mProgress.setProgress(progress);
//                    break;
//                case DOWN_OVER:
//
//                    installApk();
//                    break;
//                default:
//                    break;
//            }
//        }
//
//        ;
//    };
//
//    public UpdateManager(Context context, ViewGroup viewGroup) {
//        this.mContext = context;
//        this.mViewGroup = viewGroup;
//    }
//
//    //外部接口让主Activity调用
//    public void checkUpdateInfo() {
//        showNoticeDialog();
//    }
//
//    public void setApkUrl(String url) {
//        apkUrl = url;
//    }
//
//    private void showNoticeDialog() {
//
//        if (null == updateForceDialog) {
//            updateForceDialog = new UpdateForceDialog(mContext);
//            updateForceDialog.setOnUpdateForceListener(new UpdateForceDialog.OnUpdateForceListener() {
//                @Override
//                public void onUpdateForce() {
//
//                    downloadApk();
//                }
//
//                @Override
//                public void onInstall() {
//
//                    installApk();
//                }
//            });
//            updateForceDialog.showDialog(mViewGroup);
//        }
//
//        mProgress = updateForceDialog.getProgress();
//
////
////        AlertDialog.Builder builder = new Builder(mContext);
////        builder.setTitle("软件版本更新");
////        builder.setMessage(updateMsg);
////        builder.setPositiveButton("下载", new OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                dialog.dismiss();
////                showDownloadDialog();
////            }
////        });
////        builder.setNegativeButton("以后再说", new OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                dialog.dismiss();
////            }
////        });
////        noticeDialog = builder.create();
////        noticeDialog.show();
//    }
//
////    private void showDownloadDialog() {
////
////        AlertDialog.Builder builder = new Builder(mContext);
////        builder.setTitle("软件版本更新");
////
////        final LayoutInflater inflater = LayoutInflater.from(mContext);
////        View v = inflater.inflate(R.layout.progress, null);
////        mProgress = (ProgressBar) v.findViewById(R.id.pb_progress);
////
////        builder.setView(v);
////        builder.setNegativeButton("取消", new OnClickListener() {
////            @Override
////            public void onClick(DialogInterface dialog, int which) {
////                dialog.dismiss();
////                interceptFlag = true;
////            }
////        });
////        downloadDialog = builder.create();
////        downloadDialog.show();
////
////        downloadApk();
////    }
//
//    private Runnable mdownApkRunnable = new Runnable() {
//        @Override
//        public void run() {
//            try {
//                URL url = new URL(apkUrl);
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.connect();
//                int length = conn.getContentLength();
//                InputStream is = conn.getInputStream();
//
//                File file = new File(savePath);
//                if (!file.exists()) {
//                    file.mkdir();
//                }
//                String apkFile = saveFileName;
//                File ApkFile = new File(apkFile);
//                FileOutputStream fos = new FileOutputStream(ApkFile);
//
//                int count = 0;
//                byte buf[] = new byte[1024];
//
//                do {
//                    int numread = is.read(buf);
//                    count += numread;
//                    progress = (int) (((float) count / length) * 100);
//                    //更新进度
//                    mHandler.sendEmptyMessage(DOWN_UPDATE);
//                    if (numread <= 0) {
//                        //下载完成通知安装
//                        mHandler.sendEmptyMessage(DOWN_OVER);
//                        break;
//                    }
//                    fos.write(buf, 0, numread);
//                } while (!interceptFlag);//点击取消就停止下载.
//
//                fos.close();
//                is.close();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//    };
//
//    /**
//     * 下载apk
//     */
//
//    private void downloadApk() {
//        downLoadThread = new Thread(mdownApkRunnable);
//        downLoadThread.start();
//    }
//
//    /**
//     * 安装apk
//     */
//    private void installApk() {
//        File apkfile = new File(saveFileName);
//        if (!apkfile.exists()) {
//            return;
//        }
//        Intent i = new Intent(Intent.ACTION_VIEW);
//        i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
//        mContext.startActivity(i);
//
//    }
}
