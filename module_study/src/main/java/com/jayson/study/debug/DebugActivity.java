package com.jayson.study.debug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jayson.study.ui.fragment.StudyFragment;

import io.reactivex.annotations.Nullable;
import me.goldze.mvvmhabit.base.ContainerActivity;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：组件单独运行时的调试界面，不会被编译进release里
 */
public class DebugActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, ContainerActivity.class);
        intent.putExtra("fragment", StudyFragment.class.getCanonicalName());
        this.startActivity(intent);
        finish();
    }
}