package com.jayson.show.ui.customview.simple.doub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：手势绘制矩形
 * 第一种：先不使用双缓存
 */
public class Rect1View extends View {
    private int firstX, firstY;
    private Path path;
    private Paint paint;

    public Rect1View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //通知ScrollView请勿拦截本控件touch事件
        //(外部设置为不拦截，我们这其实可以不用设置了)
        getParent().requestDisallowInterceptTouchEvent(true);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.reset();
                firstX = x;
                firstY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //绘制矩形时，要先清除前一次的结果
                path.reset();
                path.addRect(firstX, firstY, x, y, Path.Direction.CCW);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }
}
