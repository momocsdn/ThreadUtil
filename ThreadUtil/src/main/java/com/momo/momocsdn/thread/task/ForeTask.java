package com.momo.momocsdn.thread.task;

public abstract class ForeTask extends AbsThreadTask {
    public ForeTask(boolean autoStart) {
        super(autoStart);
    }

    protected final int getOrder() {
        return 3;
    }

    public final void onBack() {
    }

    public abstract void onFore();
}