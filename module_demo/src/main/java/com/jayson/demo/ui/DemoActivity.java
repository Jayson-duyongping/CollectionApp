package com.jayson.demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jayson.demo.R;
import com.jayson.demo.ui.language.MultiLanguageActivity;


public class DemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void goChangeLanguage(View view) {
        Intent intent = new Intent(this, MultiLanguageActivity.class);
        startActivity(intent);
    }
}
