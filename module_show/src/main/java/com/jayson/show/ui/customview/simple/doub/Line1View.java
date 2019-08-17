package com.jayson.show.ui.customview.simple.doub;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：手势在屏幕上绘制曲线
 * 第一种方式：直接在 Bitmap 关联的 Canvas 上绘制直线
 */
public class Line1View extends View {

    //上一个点的坐标
    private int preX, preY;
    //当前点的坐标
    private int currentX, currentY;
    //Bitmap缓存区
    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;
    private Paint paint;

    public Line1View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
    }

    /**
     * 组件大小发生改变时回调onSizeChanged方法，我们在这创建Bitmap
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (bitmapBuffer == null) {
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            //新建Bitmap对象
            bitmapBuffer = Bitmap.createBitmap(
                    width, height, Bitmap.Config.ARGB_8888);
            bitmapCanvas = new Canvas(bitmapBuffer);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将Bitmap中的内容绘制在View中
        canvas.drawBitmap(bitmapBuffer, 0, 0, null);
    }

    /**
     * 处理手势
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //手指按下，记录第一个点的坐标
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动，记录当前点的坐标,并画一个直线
                currentX = x;
                currentY = y;
                bitmapCanvas.drawLine(preX, preY, x, y, paint);
                this.invalidate();
                //当前点的坐标成为下一个点的起始坐标
                preX = currentX;
                preY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                invalidate();
                break;
        }
        return true;
    }
}
