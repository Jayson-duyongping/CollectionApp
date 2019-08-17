package com.jayson.main.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.launcher.ARouter;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ARouter 注入
        ARouter.getInstance().inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //销毁不能在这销毁，应该是应用退出的时候销毁，这里只能暂时在主Activity中销毁
        //ARouter.getInstance().destroy();
    }
}
