package com.jayson.show.ui.customview.drawer.view;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.jayson.show.ui.customview.drawer.AttributesTool;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：绘制椭圆类
 */
public class OvalDrawer extends RectDrawer {
    public OvalDrawer(View view) {
        super(view);
    }

    @Override
    protected void drawShape(Canvas viewCanvas,
                             float firstX, float firstY,
                             float x, float y) {
        Paint paint = AttributesTool.getInstance().getPaint();
        //判断手指方向
        if (firstX < x && firstY < y) {
            //↘方向
            viewCanvas.drawOval(firstX, firstY, x, y, paint);
        } else if (firstX > x && firstY > y) {
            //↖方向
            viewCanvas.drawOval(x, y, firstX, firstY, paint);
        } else if (firstX > x && firstY < y) {
            //↙方向
            viewCanvas.drawOval(x, firstY, firstX, y, paint);
        } else if (firstX < x && firstY > y) {
            //↗方向
            viewCanvas.drawOval(firstX, y, x, firstY, paint);
        }
    }
}
