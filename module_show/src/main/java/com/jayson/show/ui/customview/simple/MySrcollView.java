package com.jayson.show.ui.customview.simple;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：解决滑动冲突，不拦截子事件
 */
public class MySrcollView extends ScrollView {
    public MySrcollView(Context context) {
        this(context, null);
    }

    public MySrcollView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySrcollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        //true拦截子事件，false不拦截子事件
        return false;
    }
}
