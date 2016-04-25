package com.lab.joke.connection;

import com.android.volley.Request;
import com.lab.joke.connection.annotation.KRequestConfig;
import com.lab.joke.exception.WException;
import com.lab.joke.environment.AppConfig;
import com.lab.joke.model.bean.Result;
import com.lab.joke.model.bean.UserInfoResult;

import java.lang.reflect.Field;

/**
 * Created by luokaiwen on 15/4/27.
 * <p/>
 * 接口请求动作名称
 */
public class WAction implements IAction {

    private static final String URL = AppConfig.INSTANCE.getUrlPrefix();
    private static final int POST = Request.Method.POST;
    private static final int GET = Request.Method.GET;

    private static WAction instance;

    private WAction() {
    }

    public static WAction getInstance() {
        if (instance == null) {
            instance = new WAction();
        }
        return instance;
    }

    //=============================Account Setting===============================//

    /**
     * 登录
     */
    @KRequestConfig(method = POST, url = "login", clazz = UserInfoResult.class, index = 2401)
    public static final String LOGIN = "LOGIN";

    @Override
    public String getAction(int index) {
        String action = "";
        Field[] fields = getClass().getFields();
        for (Field field : fields) {
            KRequestConfig annotation = field.getAnnotation(KRequestConfig.class);
            if (annotation != null && annotation.index() == index) {
                try {
                    action = (String) field.get(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return action;
    }

    @Override
    public String getUrl(String action) {
        String url = null;
        KRequestConfig annotation = getAnnotation(action);
        if (annotation != null) {
            url = AppConfig.INSTANCE.getUrlPrefix() + annotation.url();
        } else {
            try {
                throw new WException("url cannot be null, please configure in WAction first!");
            } catch (WException e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    @Override
    public int getIndex(String action) {
        int index = -1;
        KRequestConfig annotation = getAnnotation(action);
        if (annotation != null) {
            index = annotation.index();
        } else {
            try {
                throw new WException("index cannot be null, please configure in WAction first!");
            } catch (WException e) {
                e.printStackTrace();
            }
        }
        return index;
    }

    @Override
    public int getMethod(String action) {
        int method = -1;
        KRequestConfig annotation = getAnnotation(action);
        if (annotation != null) {
            method = annotation.method();
        } else {
            try {
                throw new WException("method cannot be null, please configure in WAction first!");
            } catch (WException e) {
                e.printStackTrace();
            }
        }
        return method;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends Result> getResultType(String action) {
        Class<? extends Result> clazz = null;
        KRequestConfig annotation = getAnnotation(action);
        if (annotation != null) {
            clazz = annotation.clazz();
        } else {
            try {
                throw new WException("clazz cannot be null,please configure in WAction first!");
            } catch (WException e) {
                e.printStackTrace();
            }
        }
        return clazz;
    }

    private KRequestConfig getAnnotation(String action) {
        Field[] fields = getClass().getFields();
        for (Field field : fields) {
            try {
                if (action.equals(field.get(field.getName()))) {
                    KRequestConfig annotation = field.getAnnotation(KRequestConfig.class);
                    return annotation;
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String joinUrl(String host, int port, String service) {
        return host + ":" + port + service;
    }
}
