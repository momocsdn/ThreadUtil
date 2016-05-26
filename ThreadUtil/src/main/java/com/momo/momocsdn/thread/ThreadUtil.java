package com.momo.momocsdn.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

public class ThreadUtil {
    private static Handler uiHandler = null;
    private static ScheduledExecutorService pool = null;
    private static ScheduledExecutorService poolHigh = null;

    public ThreadUtil() {
    }

    private static void checkInited() {
        if (uiHandler == null) {
            throw new IllegalStateException("ThreadUtil.init NOT inited, you must call ThreadUtil.init first");
        }
    }

    public static Handler getUIHandler() {
        checkInited();
        return uiHandler;
    }

    public static ScheduledExecutorService getPool() {
        checkInited();
        return pool;
    }

    public static ScheduledExecutorService getPoolHigh() {
        checkInited();
        return poolHigh;
    }

    public static synchronized void shutdown() {
        uiHandler = null;
        if (pool != null) {
            pool.shutdown();
        }

        if (poolHigh != null) {
            poolHigh.shutdown();
        }

    }

    public static synchronized void shutdownNow() {
        uiHandler = null;
        if (pool != null) {
            pool.shutdownNow();
        }

        if (poolHigh != null) {
            poolHigh.shutdownNow();
        }

    }

    public static synchronized void init(int poolSize, int highPoolSize) {
        if (uiHandler != null) {
            throw new IllegalStateException("ThreadUtil.init already inited");
        } else {
            uiHandler = new Handler(Looper.getMainLooper());
            if (poolSize > 0) {
                pool = Executors.newScheduledThreadPool(poolSize, new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setPriority(1);
                        t.setDaemon(true);
                        return t;
                    }
                });
            }

            if (highPoolSize > 0) {
                poolHigh = Executors.newScheduledThreadPool(highPoolSize, new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setPriority(4);
                        t.setDaemon(true);
                        return t;
                    }
                });
            }

        }
    }
}
