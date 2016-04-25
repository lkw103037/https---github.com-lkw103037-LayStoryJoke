package com.lab.joke.model.bean;

/**
 * Created by rokevin on 15/10/5.
 * <p/>
 * 语音类
 */
public class Speech extends Bean {

    private String url;

    private String lenght;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    @Override
    public String toString() {
        return "Speech{" +
                "url='" + url + '\'' +
                ", lenght='" + lenght + '\'' +
                '}';
    }
}
