package com.jayson.demo.ui.text_selected;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.jayson.demo.R;

import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * 1.文字选中样式通过修改样式确定
 * 2.软键盘搜索监听
 */
public class TextSelectedActivity extends AppCompatActivity {

    private EditText testEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_selected);
        testEt = findViewById(R.id.test_et);
        //监听软键盘搜索
        testEt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                ToastUtils.showShort("点击确定");
                return true;
            }
            return false;
        });
    }
}
