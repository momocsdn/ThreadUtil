package com.momo.momocsdn.thread.task;

public abstract class BackForeTask extends AbsThreadTask {
    public BackForeTask(boolean autoStart) {
        super(autoStart);
    }

    public BackForeTask(boolean autoStart, boolean highPriority) {
        super(autoStart, highPriority);
    }

    protected final int getOrder() {
        return 0;
    }

    public abstract void onBack();

    public abstract void onFore();
}