package com.jayson.show.ui.customview;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivityViewBinding;

/**
 * 自定义View
 */
public class ViewActivity extends AppCompatActivity {

    ActivityViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_view);
    }

    /**
     * 刷新
     *
     * @param view
     */
    public void refresh(View view) {
        binding.codeView.refresh();
    }

    /**
     * 改变颜色
     *
     * @param view
     */
    public void changeColor(View view) {
        binding.codeView.setColor(Color.BLUE);
    }

    /**
     * 改变字体大小
     *
     * @param view
     */
    public void changeFont(View view) {
        binding.codeView.setFontSize(100);
    }

    /**
     * 改变随机数个数
     *
     * @param view
     */
    public void textCount(View view) {
        binding.codeView.setCount(5);
    }

    /**
     * 改变干扰线条数
     *
     * @param view
     */
    public void lineCount(View view) {
        binding.codeView.setLineCount(150);
    }

    /**
     * 获取验证码
     *
     * @param view
     */
    public void get(View view) {
        String code = binding.codeView.theCode();
        Toast.makeText(this, code, Toast.LENGTH_LONG).show();
    }
}
