package com.lab.joke.util;

import android.os.Environment;
import android.text.TextUtils;

import com.lab.joke.environment.Constant;

import java.io.File;

/**
 * Created by luokaiwen on 15/5/31.
 * <p/>
 * 处理SDCard内容
 */
public class SDCardUtil {

    /**
     * 初始化项目中用到的文件夹
     */
    public static void initFileDir() {

        try {
            File file = new File(Constant.IMAGE_DIR);

            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取文件夹大小
     *
     * @param fileDir File路径
     * @return long
     */
    public static long getFolderSize(String fileDir) {

        long size = 0;
        try {

            File file = new File(fileDir);

            if (file.exists() && file.isDirectory()) {// 处理目录

                File[] fileList = file.listFiles();
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].isDirectory()) {
                        size = size + getFolderSize(fileList[i].getAbsolutePath());

                    } else {
                        size = size + fileList[i].length();

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //return size/1048576;
        return size / 1024;
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
                initFileDir();
            } catch (Exception e) {
                e.printStackTrace();
                initFileDir();
            }
        }
    }

    public static String getAmrPath(String name) {
        File file = new File(Environment
                .getExternalStorageDirectory(), "my/" + name + ".amr");
        return file.getAbsolutePath();
    }
}
