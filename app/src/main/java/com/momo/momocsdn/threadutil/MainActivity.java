package com.momo.momocsdn.threadutil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.momo.momocsdn.thread.ThreadUtil;
import com.momo.momocsdn.thread.task.BackForeTask;
import com.momo.momocsdn.threadutil.mvc.controller.LoginLogic;
import com.momo.momocsdn.threadutil.mvc.observer.LoginObserver;

public class MainActivity extends AppCompatActivity implements LoginObserver{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginLogic.getInstance().addObserver(this);

        init();
    }

    private void init() {
        findViewById(R.id.btn_backforetask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginLogic.getInstance().commitLoginRequest("momocsdn","123456");
            }
        });

        findViewById(R.id.btn_forebacktask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.btn_backtask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        findViewById(R.id.btn_foretask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        LoginLogic.getInstance().removeObserver(this);
        super.onDestroy();
    }

    @Override
    public void onLoginSuccess() {
        Toast.makeText(this,"Login Success!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailed(String errorMsg) {
        Toast.makeText(this,errorMsg,Toast.LENGTH_SHORT).show();
    }
}
