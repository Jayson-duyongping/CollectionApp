package com.jayson.study.ui.view_model;

import android.app.Application;
import android.support.annotation.NonNull;
import android.view.View;

import com.jayson.study.ui.activity.TestActivity;

import me.goldze.mvvmhabit.base.BaseViewModel;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 */
public class StudyViewModel extends BaseViewModel {
    public StudyViewModel(@NonNull Application application) {
        super(application);
    }

    public View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(TestActivity.class);
        }
    };
}
