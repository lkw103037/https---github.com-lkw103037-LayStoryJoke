package com.lab.joke.connection;


import com.lab.joke.model.bean.Result;

interface IParser {
    public Result parse(String response, Class<? extends Result> clazz);
}
