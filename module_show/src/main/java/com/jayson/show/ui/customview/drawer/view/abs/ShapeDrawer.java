package com.jayson.show.ui.customview.drawer.view.abs;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

import com.jayson.show.ui.customview.drawer.BitmapBuffer;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：一个抽象类
 * 用于处理图形的绘制、逻辑、和手势响应
 */
public abstract class ShapeDrawer {
    private View view;

    public ShapeDrawer(View view) {
        super();
        this.view = view;
    }

    public View getView() {
        return view;
    }

    /**
     * 用于绘图
     *
     * @param viewCanvas
     */
    public void draw(Canvas viewCanvas) {
        //画历史结果
        Bitmap bitmap = BitmapBuffer.getInstance().getBitmap();
        viewCanvas.drawBitmap(bitmap, 0, 0, null);
    }

    /**
     * 用于响应触摸事件
     *
     * @param event
     * @return
     */
    public abstract boolean onTouchEvent(MotionEvent event);

    /**
     * 绘图的逻辑
     */
    public abstract void logic();
}
