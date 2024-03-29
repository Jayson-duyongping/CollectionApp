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
 * 创建内容：手势绘制矩形
 * 第一种：使用双缓存保存绘制历史
 */
public class Rect2View extends View {
    private int firstX, firstY;
    private Path path;
    private Paint paint;
    private Bitmap bitmapBuffer;
    private Canvas bitmapCanvas;

    public Rect2View(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        path = new Path();
    }

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
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = x;
                firstY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //绘制矩形时，要先清除前一次的结果
                path.reset();
                if (firstX < x && firstY < y) {
                    //↘方向
                    path.addRect(firstX, firstY, x, y, Path.Direction.CCW);
                } else if (firstX > x && firstY > y) {
                    //↖方向
                    path.addRect(x, y, firstX, firstY, Path.Direction.CCW);
                } else if (firstX > x && firstY < y) {
                    //↙方向
                    path.addRect(x, firstY, firstX, y, Path.Direction.CCW);
                } else if (firstX < x && firstY > y) {
                    //↗方向
                    path.addRect(firstX, y, x, firstY, Path.Direction.CCW);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                bitmapCanvas.drawPath(path, paint);
                invalidate();
                break;
        }
        return true;
    }
}
