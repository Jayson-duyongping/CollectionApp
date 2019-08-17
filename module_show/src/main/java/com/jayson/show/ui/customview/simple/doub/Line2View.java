package com.jayson.show.ui.customview.simple.doub;

import android.content.Context;
import android.graphics.Bitmap;
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
 * 创建内容：手势在屏幕上绘制曲线
 * 第二种方式：通过Path来绘图，不管从功能上还是效率上这都是更优的选择
 */
public class Line2View extends View {

    //上一个点的坐标
    private int preX, preY;
    private Path path;
    private Paint paint;
    //路径虽然可以实现手指移动轨迹，但是下次绘制，上一条路径会消失，
    //所以使用双缓存来实现记录历史
    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;

    public Line2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        path = new Path();
    }

    /**
     * 一般我们初始化双缓存就在这个里面初始化
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
        canvas.drawBitmap(bitmapBuffer, 0, 0, null);
        //绘制路径
        canvas.drawPath(path, paint);
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
                path.reset();
                preX = x;
                preY = y;
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动，绘制贝塞尔曲线
                //控制点和起点一样
                //path.quadTo(preX, preY, x, y);
                //控制点计算
                path.quadTo((x + preX) / 2, (y + preY) / 2, x, y);
                this.invalidate();
                //当前点的坐标成为下一个点的起始坐标
                preX = x;
                preY = y;
                break;
            case MotionEvent.ACTION_UP:
                //手指松开后将最终的绘图结果绘制在bitmapBuffer中，同时绘制到View上
                bitmapCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;
    }
}
