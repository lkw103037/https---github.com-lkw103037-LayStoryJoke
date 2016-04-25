package com.lab.joke.model.bean;

/**
 * Created by luokaiwen on 15/9/18.
 * <p/>
 * 键值对
 */
public class Item extends Bean {

    /**
     * 名称
     */
    private String name;

    /**
     * 值
     */
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
