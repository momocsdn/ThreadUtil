package com.momo.momocsdn.threadutil.mvc.observer;

public interface LoginObserver {
    public void onLoginSuccess();
    public  void onLoginFailed(String errorMsg);
}
