package com.jayson.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * 启动页面
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                inMain();
            }
        }, 3 * 1000);
    }

    private void inMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
