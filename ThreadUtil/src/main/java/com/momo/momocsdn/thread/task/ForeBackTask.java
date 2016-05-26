package com.momo.momocsdn.thread.task;

public abstract class ForeBackTask extends AbsThreadTask {
    public ForeBackTask(boolean autoStart) {
        super(autoStart);
    }

    public ForeBackTask(boolean autoStart, boolean highPriority) {
        super(autoStart, highPriority);
    }

    protected final int getOrder() {
        return 1;
    }

    public abstract void onFore();

    public abstract void onBack();
}
