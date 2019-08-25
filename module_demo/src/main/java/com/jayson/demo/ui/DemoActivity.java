package com.jayson.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jayson.demo.R;
import com.jayson.demo.ui.language.MultiLanguageActivity;
import com.jayson.demo.ui.language.bean.LanguageRegion;
import com.jayson.demo.ui.language.utils.LanguageUtils;
import com.jayson.demo.ui.text_selected.TextSelectedActivity;


public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        //这个在项目中应该写到application中
        //判断当前的语言区域
        LanguageRegion currentRegion = LanguageUtils.getLanguage(getApplicationContext());
        //设置语言
        LanguageUtils.setLanguage(this,
                currentRegion.getLocale());
    }

    public void goChangeLanguage(View view) {
        Intent intent = new Intent(this, MultiLanguageActivity.class);
        startActivity(intent);
    }

    public void goTextSelected(View view) {
        Intent intent = new Intent(this, TextSelectedActivity.class);
        startActivity(intent);
    }
}
