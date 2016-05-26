package com.momo.momocsdn.threadutil;


import android.app.Application;

import com.momo.momocsdn.thread.ThreadUtil;

public class ThreadUtilApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ThreadUtil.init(3,2);
    }
}
