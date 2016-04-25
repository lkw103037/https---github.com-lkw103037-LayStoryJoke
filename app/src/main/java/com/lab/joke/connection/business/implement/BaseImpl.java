package com.lab.joke.connection.business.implement;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.lab.joke.connection.ActionListener;
import com.lab.joke.connection.ActionRequest;
import com.lab.joke.connection.ConnectionManager;
import com.lab.joke.connection.multipart.MultiPartStringRequest;
import com.lab.joke.controler.IActionListener;
import com.lab.joke.model.bean.Result;
import com.lab.joke.model.bean.ResultState;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luokaiwen on 15/5/8.
 * <p/>
 * 基础实现类
 */
public class BaseImpl {

    Context mContext;
    ConnectionManager mConnectionManager;
    RequestQueue mRequestQueue;
    RequestQueue mRequestQueueMultipart;
    IActionListener iActionListener;

    public BaseImpl(Context context, IActionListener iActionListener) {
        mContext = context;
        mConnectionManager = ConnectionManager.INSTANCE;
        mRequestQueue = mConnectionManager.getRequestQueue(mContext);
        mRequestQueueMultipart = mConnectionManager.getMultipartQueue(mContext);
        this.iActionListener = iActionListener;
    }

    protected void request(String action, HashMap<String, String> params, String tag) {
        ActionRequest actionRequest = new ActionRequest(action, params, new ActionListener<Result>() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Result result = new Result();
                result.setAction("NetError");

                ResultState resultState = new ResultState();
                resultState.setStateCode("99999");
                resultState.setStateMessage("");
                //TODO  后续需要处理
                result.setState(resultState);

                iActionListener.onActionFail(result);
            }

            @Override
            public void onResponse(Result response) {

                if (response.getState().getStateCode().equals("0")) {
                    iActionListener.onActionSucc(response);
                } else {
                    iActionListener.onActionFail(response);
                }

            }
        });

        mRequestQueue.add(actionRequest).setTag(tag);
    }

    protected void requestMultipart(String action, final HashMap<String, String> params, final HashMap<String, File> fileParams, String tag) {

        MultiPartStringRequest actionRequest = new MultiPartStringRequest(action, params, new ActionListener<Result>() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Result result = new Result();
                result.setAction("NetError");

                ResultState resultState = new ResultState();
                resultState.setStateCode("99999");
                resultState.setStateMessage("");
                //TODO  后续需要处理
                result.setState(resultState);

                iActionListener.onActionFail(result);
            }

            @Override
            public void onResponse(Result response) {

                if (response.getState().getStateCode().equals("0")) {
                    iActionListener.onActionSucc(response);
                } else {
                    iActionListener.onActionFail(response);
                }

            }
        }) {
            @Override
            public Map<String, File> getFileUploads() {
                return fileParams;
            }

            @Override
            public Map<String, String> getStringUploads() {
                return params;
            }
        };

        mRequestQueueMultipart.add(actionRequest).setTag(tag);
    }
}
