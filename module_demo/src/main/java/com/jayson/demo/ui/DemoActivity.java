package com.jayson.demo.ui;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.jayson.demo.R;
import com.jayson.demo.ui.language.MultiLanguageActivity;
import com.jayson.demo.ui.language.bean.LanguageRegion;
import com.jayson.demo.ui.language.utils.LanguageUtils;
import com.jayson.demo.ui.text_selected.TextSelectedActivity;


public class DemoActivity extends AppCompatActivity {

    private AudioManager audioManager;

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

    public void switchCallPlayer(View view) {
        if (audioManager == null) {
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        }
        int currVolume = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);
        try {
            if (!audioManager.isSpeakerphoneOn()) {
                Toast.makeText(this, "免提模式", Toast.LENGTH_LONG).show();
                audioManager.setSpeakerphoneOn(true);//开启免提
                audioManager.setMode(AudioManager.MODE_NORMAL);
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL),
                        AudioManager.STREAM_VOICE_CALL);
            } else {
                audioManager.setSpeakerphoneOn(false);
                //5.0以上
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                } else {
                    audioManager.setMode(AudioManager.MODE_IN_CALL);
                }
                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, currVolume,
                        AudioManager.STREAM_VOICE_CALL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
