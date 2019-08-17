package com.jayson.show.ui.customview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivityGraphics2dBinding;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 展示普通绘制
 */
public class Graphics2DActivity extends AppCompatActivity {

    ActivityGraphics2dBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_graphics2d);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                //延时200ms开始计时，每50ms重绘
                binding.ballView.postInvalidate();
            }
        }, 200, 50);
        binding.watchView.run();
    }
}
