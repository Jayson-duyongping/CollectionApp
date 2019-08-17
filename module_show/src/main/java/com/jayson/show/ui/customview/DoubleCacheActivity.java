package com.jayson.show.ui.customview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivityDoubleCacheBinding;

/**
 * 双缓存技术
 * 就是有两个绘图区，一个是 Bitmap 的 Canvas，另一个就是当前View 的 Canvas。
 * 先将图形绘制在 Bitmap 上，然后再将 Bitmap 绘制在 View 上，也就是说，我们
 * 在 View 上看到的效果其实就是 Bitmap 上的内容。
 * 1.高绘图性能
 * 2.可以在屏幕上展示绘图的过程
 */
public class DoubleCacheActivity extends AppCompatActivity {

    ActivityDoubleCacheBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_double_cache);
    }
}
