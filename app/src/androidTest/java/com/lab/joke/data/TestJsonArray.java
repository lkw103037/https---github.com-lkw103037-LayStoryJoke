package com.lab.joke.data;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * Created by luokaiwen on 15/6/9.
 */
public class TestJsonArray {

    public static void main(String[] args) {

        String data = "[{\"你好\"},{\"你好2\"},{\"你好3\"},]";

        TestData testData = JSON.parseObject(data, TestData.class);
        System.out.print("testData is:" + testData.toString());
    }

    public class TestData {

        private ArrayList<String> data;

        public ArrayList<String> getData() {
            return data;
        }

        public void setData(ArrayList<String> data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "TestData{" +
                    "data=" + data +
                    '}';
        }
    }
}
