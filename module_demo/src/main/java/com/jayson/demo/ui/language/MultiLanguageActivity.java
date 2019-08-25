package com.jayson.demo.ui.language;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jayson.demo.R;
import com.jayson.demo.ui.language.adapter.LanguageAdapter;
import com.jayson.demo.ui.language.utils.LanguageUtils;
import com.jayson.demo.ui.language.utils.SpHelper;

public class MultiLanguageActivity extends AppCompatActivity {

    private RecyclerView languageRv;
    private LanguageAdapter languageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_language);
        languageRv = findViewById(R.id.language_rv);
        //列表设置
        languageRv.setLayoutManager(new LinearLayoutManager(this));
        languageAdapter = new LanguageAdapter(this);
        languageRv.setAdapter(languageAdapter);
        //点击
        languageAdapter.setOnItemClickListener(region -> {
            //设置语言
            LanguageUtils.setLanguage(this,
                    region.getLocale());
            //缓存下来
            SpHelper.getInstance(getApplicationContext())
                    .saveBeanByFastJson("currentLanguage", region);
            //关闭页面
            finish();
        });
    }
}
