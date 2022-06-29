package com.exam.entity;

import java.util.ArrayList;

/**
 * 创建这个泛型赶紧拓展性比arraylist那种强一些 以后能加一些自己的功能 ==这只是我自己的理解
 *
 * @param <T>
 */
public class ReadItem<T extends String> {
    public ArrayList<T> readitemlist = new ArrayList<T>();


    public ArrayList<T> getReadItem() {
        return readitemlist;
    }

    public void setReadItem(ArrayList<T> readItem) {
        this.readitemlist = readItem;
    }

    public ReadItem(T... es) {
        for (T e : es
        ) {
            this.readitemlist.add(e);
        }
    }

    //通过add直接增加元素
    public ReadItem<T> add(T t) {
        this.readitemlist.add(t);
        return this;
    }

    //以string形式获取item值
    public String getAll() {
        if (readitemlist == null)
            return "暂无添加";
        else return (readitemlist.toString());
    }

    //获取长度
    public int length() {
        return readitemlist.size();
    }

}
