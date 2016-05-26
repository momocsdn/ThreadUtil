package com.momo.momocsdn.threadutil.mvc.controller;

import android.os.SystemClock;

import com.momo.momocsdn.thread.task.BackForeTask;
import com.momo.momocsdn.threadutil.mvc.observer.LoginObserver;
import com.momo.momocsdn.threadutil.util.Singlton;

import java.util.List;

public class LoginLogic extends BaseLogic<LoginObserver> {
    public static LoginLogic getInstance(){
        return Singlton.getInstance(LoginLogic.class);
    }


    public void commitLoginRequest(final String pUserName,final String pUserPwd){
        new BackForeTask(true){
            @Override
            public void onBack() {
                /** ------- mock network request ------*/
                SystemClock.sleep(3000);
            }

            @Override
            public void onFore() {
                double randomValue = Math.random()*10;
                if(randomValue < 5){
                    fireLoginSuccess();
                }else{
                    fireLoginFailed(pUserName + "Login Failed --> " + pUserPwd);
                }
            }
        };
    }


    private void fireLoginSuccess(){
        List<LoginObserver> tmpList = getObservers();
        for (LoginObserver o : tmpList) {
            o.onLoginSuccess();
        }
    }

    private void fireLoginFailed(String errorMsg){
        List<LoginObserver> tmpList = getObservers();
        for (LoginObserver o : tmpList) {
            o.onLoginFailed(errorMsg);
        }
    }
}
