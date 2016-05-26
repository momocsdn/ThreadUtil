package com.momo.momocsdn.thread.task;

import android.util.Log;

import com.momo.momocsdn.thread.ThreadUtil;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by momocsdn on 16/5/26.
 */
public abstract class AbsThreadTask {
    public static final int BACK_FORE = 0;
    public static final int FORE_BACK = 1;
    public static final int BACK = 2;
    public static final int FORE = 3;
    private boolean highPriority;
    private boolean started;

    public AbsThreadTask(boolean autoStart) {
        this(autoStart, false);
    }

    public AbsThreadTask(boolean autoStart, boolean highPriority) {
        this.highPriority = false;
        this.started = false;
        this.highPriority = highPriority;
        if (autoStart) {
            this.start();
        }

    }

    private void callFore(final boolean isHight, final boolean callBack) {
        ThreadUtil.getUIHandler().post(new Runnable() {
            public void run() {
                try {
                    onFore();
                } catch (Exception var2) {
                    Log.e("ThreadTask", "onFore", var2);
                }

                if (callBack) {
                    callBack(isHight, false);
                }

            }
        });
    }

    private void callBack(final boolean isHigh, final boolean callFore) {
        ScheduledExecutorService es;
        if (isHigh) {
            es = ThreadUtil.getPoolHigh();
        } else {
            es = ThreadUtil.getPool();
        }

        es.submit(new Runnable() {
            public void run() {
                try {
                    onBack();
                } catch (Exception var2) {
                    Log.e("ThreadTask", "onFore", var2);
                }

                if (callFore) {
                    callFore(isHigh, false);
                }

            }
        });
    }

    public void start() {
        if (!this.started) {
            this.started = true;
            int order = this.getOrder();
            if (order != 3 && order != 1) {
                if (order == 2 || order == 0) {
                    this.callBack(this.highPriority, order == 0);
                }

            } else {
                this.callFore(this.highPriority, order == 1);
            }
        }
    }

    protected abstract int getOrder();

    public abstract void onBack();

    public abstract void onFore();

}
