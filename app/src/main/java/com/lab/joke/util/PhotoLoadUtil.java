package com.lab.joke.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.Toast;

import com.lab.joke.util.common.LogUtil;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by luokaiwen on 15/6/23.
 * <p/>
 * 图片加载帮助类
 */
public class PhotoLoadUtil {

    public static final int HANDLE_PHOTO_PICK = 0x110;
    public static final int HANDLE_PHOTO_PICK_COMPLATE = 0x111;

    int totalCount = 0;

    /**
     * 临时的辅助类，用于防止同一个文件夹的多次扫描
     */
    private HashSet<String> mDirPaths = new HashSet<String>();
    private ArrayList<PhotoPick> mPhotoPicks = new ArrayList<PhotoPick>();

    /**
     * 利用ContentProvider扫描手机中的图片，此方法在运行在子线程中 完成图片的扫描，最终获得jpg最多的那个文件夹
     */
    public void getImages(final Context context, final Handler mHandler) {

        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            Toast.makeText(context, "暂无外部存储", Toast.LENGTH_SHORT).show();
            return;
        }

        // 显示进度条
        //ProgressDialog mProgressDialog = ProgressDialog.show(context, null, "正在加载...");

        new Thread(new Runnable() {

            @Override
            public void run() {

                String firstImage = null;

                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = context.getContentResolver();

                // 只查询jpeg和png的图片
//                Cursor mCursor = mContentResolver.query(mImageUri, null,
//                        MediaStore.Images.Media.MIME_TYPE + "=? or "
//                                + MediaStore.Images.Media.MIME_TYPE + "=?",
//                        new String[]{"image/jpeg", "image/png"},
//                        MediaStore.Images.Media.DATE_MODIFIED);

                final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.ImageColumns.ORIENTATION, MediaStore.Images.Media.SIZE, MediaStore.Images.ImageColumns.MIME_TYPE};
                final String orderBy = MediaStore.Images.Media.DATE_ADDED + " DESC";
                final String[] selectionArgs = {"20000", "image/jpeg", "image/jpg", "image/png"};//限制大小20K,图片格式为 jpeg jpg png
                final String selector = MediaStore.Images.Media.SIZE + ">?" + " AND (" + MediaStore.Images.Media.MIME_TYPE + "=?" + " OR " + MediaStore.Images.Media.MIME_TYPE + "=?" + " OR " + MediaStore.Images.Media.MIME_TYPE + "=?" + " OR " + MediaStore.Images.Media.MIME_TYPE + "=?" + ")";
                Cursor mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, selector, selectionArgs, orderBy);

                //LogUtil.e("TAG", mCursor.getCount() + "");

                while (mCursor.moveToNext()) {

                    // 获取图片的路径
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));

                    LogUtil.e("TAG", path);

                    // 拿到第一张图片的路径
                    if (firstImage == null)
                        firstImage = path;

                    // 获取该图片的父路径名
                    File parentFile = new File(path).getParentFile();

                    if (parentFile == null)
                        continue;

                    String dirPath = parentFile.getAbsolutePath();

                    PhotoPick photoPick = null;
                    // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
//                    if (mDirPaths.contains(dirPath)) {
//                        continue;
//                    } else {
//
//                        mDirPaths.add(dirPath);
//                    }

                    // 初始化imageFloder
                    photoPick = new PhotoPick();
                    photoPick.setDir(path);

                    int picSize = parentFile.list(new FilenameFilter() {
                        @Override
                        public boolean accept(File dir, String filename) {
                            if (filename.endsWith(".jpg")
                                    || filename.endsWith(".png")
                                    || filename.endsWith(".jpeg"))
                                return true;
                            return false;
                        }
                    }).length;
                    totalCount += picSize;

                    photoPick.setCount(picSize);

//                    Message message = mHandler.obtainMessage();
//                    message.what = HANDLE_PHOTO_PICK;
//                    message.obj = photoPick;
//                    mHandler.sendMessage(message);
                    mPhotoPicks.add(photoPick);
                }

                Message message = mHandler.obtainMessage();
                message.what = HANDLE_PHOTO_PICK;
                message.obj = mPhotoPicks;
                mHandler.sendMessage(message);

                //mHandler.sendEmptyMessage(HANDLE_PHOTO_PICK_COMPLATE);

                mCursor.close();

                // 扫描完成，辅助的HashSet也就可以释放内存了
                mDirPaths = null;
            }
        }).start();
    }

    public class PhotoPick {

        private String dir;

        private int count;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public String toString() {
            return "PhotoPick{" +
                    "dir='" + dir + '\'' +
                    ", count='" + count + '\'' +
                    '}';
        }
    }
}
