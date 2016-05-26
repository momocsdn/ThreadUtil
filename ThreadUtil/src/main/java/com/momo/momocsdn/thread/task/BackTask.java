package com.momo.momocsdn.thread.task;

public abstract class BackTask extends AbsThreadTask {
    public BackTask(boolean autoStart) {
        super(autoStart);
    }

    public BackTask(boolean autoStart, boolean highPriority) {
        super(autoStart, highPriority);
    }

    protected final int getOrder() {
        return 2;
    }

    public abstract void onBack();

    public final void onFore() {
    }
}