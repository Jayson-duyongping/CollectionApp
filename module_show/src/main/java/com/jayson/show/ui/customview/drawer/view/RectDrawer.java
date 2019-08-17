package com.jayson.show.ui.customview.drawer.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.jayson.show.ui.customview.drawer.AttributesTool;
import com.jayson.show.ui.customview.drawer.BitmapBuffer;
import com.jayson.show.ui.customview.drawer.view.abs.ShapeDrawer;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：绘制矩形类
 */
public class RectDrawer extends ShapeDrawer {
    private float firstX;
    private float firstY;
    private float currentX;
    private float currentY;

    public RectDrawer(View view) {
        super(view);
    }

    @Override
    public void draw(Canvas viewCanvas) {
        super.draw(viewCanvas);
        //绘制矩形
        drawShape(viewCanvas, firstX, firstY, currentX, currentY);
    }

    /**
     * 画当前的形状
     */
    protected void drawShape(Canvas viewCanvas,
                             float firstX, float firstY,
                             float x, float y) {
        Paint paint = AttributesTool.getInstance().getPaint();
        //判断手指方向
        if (firstX < x && firstY < y) {
            //↘方向
            viewCanvas.drawRect(firstX, firstY, x, y, paint);
        } else if (firstX > x && firstY > y) {
            //↖方向
            viewCanvas.drawRect(x, y, firstX, firstY, paint);
        } else if (firstX > x && firstY < y) {
            //↙方向
            viewCanvas.drawRect(x, firstY, firstX, y, paint);
        } else if (firstX < x && firstY > y) {
            //↗方向
            viewCanvas.drawRect(firstX, y, x, firstY, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = x;
                firstY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                currentX = x;
                currentY = y;
                getView().invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //将最终的矩形绘制在缓冲区
                Canvas canvas = BitmapBuffer.getInstance().getCanvas();
                drawShape(canvas, firstX, firstY, currentX, currentY);
                getView().invalidate();
                //保存到撤销栈中
                BitmapBuffer.getInstance().pushBitmap();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void logic() {

    }
}
