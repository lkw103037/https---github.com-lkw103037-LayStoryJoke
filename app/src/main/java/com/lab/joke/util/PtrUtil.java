package com.lab.joke.util;

/**
 * Created by luokaiwen on 15/9/15.
 * <p/>
 * 下拉刷新帮助类
 */
public class PtrUtil {

    private String dir;
    private String preId;
    private String count;

    public PtrUtil(String dir, String preId, String count) {

        this.dir = dir;
        this.preId = preId;
        this.count = count;
    }

    public void loadCurren() {

        dir = "0";
        preId = "0";
        count = "10";
    }

    public void loadBefore(String preId) {

        dir = "1";
        this.preId = preId;
        count = "10";
    }

    public void loadMore(String preId) {

        dir = "0";
        this.preId = preId;
        count = "10";
    }

    public void setCount(int count) {

        // 第一次加载如果count为0则是空数据
        // 第一次有数据，count为零不处理
        // 第一次有数据，count>0,下拉加载
    }

    public interface IPtrListener {

        void loadBefore();

        void loadMore();

        void empty();
    }
}
