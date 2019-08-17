package com.jayson.show.ui.viewpager.view;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayson.show.R;

/**
 * 创建人：jayson
 * 创建时间：2019/8/6
 * 创建内容：自定义view
 */
public class TabView extends FrameLayout {

    private ImageView iconIv;
    private ImageView iconSelectIv;
    private TextView titleTv;

    private static final int COLOR_DEFAULT = Color.parseColor("#ff000000");
    private static final int COLOR_SELECT = Color.parseColor("#ff45C01A");

    public TabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //加载布局
        inflate(context, R.layout.tab_view, this);

        iconIv = findViewById(R.id.icon_iv);
        iconSelectIv = findViewById(R.id.icon_select_iv);
        titleTv = findViewById(R.id.title_tv);
        //默认(默认灰色)
        setProgress(0);
    }

    /**
     * 设置进度样式
     *
     * @param progress
     */
    public void setProgress(float progress) {
        iconIv.setAlpha(1 - progress);
        iconSelectIv.setAlpha(progress);
        //Argb的一个估值器
        ArgbEvaluator evaluator = new ArgbEvaluator();
        titleTv.setTextColor((Integer) evaluator.evaluate(progress, COLOR_DEFAULT, COLOR_SELECT));
    }

    /**
     * 设置icon,text
     * 方式一：抽取自定义属性，通过xml设置
     * 方式二：直接对外开放方法设置
     */
    public void setIconAndText(int icon, int iconSelect, String text) {
        iconIv.setImageResource(icon);
        iconSelectIv.setImageResource(iconSelect);
        titleTv.setText(text);
    }
}
