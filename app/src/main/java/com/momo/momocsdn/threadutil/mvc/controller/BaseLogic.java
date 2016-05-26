package com.momo.momocsdn.threadutil.mvc.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 * @Description:逻辑处理基类，主要 用于添加/移除观察者
 */
public class BaseLogic<T> {

    /**
     * 观察者列表
     */
    private List<T> observers = new ArrayList<T>();

    /**
     * @Description:添加观察者
     */
    public synchronized void addObserver(T observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * 移除观察者
     */
    public synchronized void removeObserver(T observer) {
        observers.remove(observer);
    }

    /**
     * 清空观察者
     */
    public synchronized void clearObservers() {
        this.observers.clear();
    }

    /**
     * 返回只读的所有的监听器列表
     */
    public synchronized List<T> getObservers() {
        List<T> list = new ArrayList<T>(observers.size());

        list.addAll(observers);

        return list;
    }

}
