package com.lab.joke.connection;

import com.alibaba.fastjson.JSON;
import com.lab.joke.model.bean.Result;

/**
 * Created by luokaiwen on 16/4/18.
 */
public class WParser {

    private static WParser instance;

    private WParser() {

    }

    public static WParser getInstance() {
        if (instance == null) {
            instance = new WParser();
        }
        return instance;
    }

    public Result parse(String raw, Class<? extends Result> clazz) {
        Result result = JSON.parseObject(raw, clazz);
        return result;
    }
}
