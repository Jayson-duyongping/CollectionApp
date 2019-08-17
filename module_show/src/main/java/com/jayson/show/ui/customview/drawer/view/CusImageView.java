package com.jayson.show.ui.customview.drawer.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jayson.show.ui.customview.drawer.BitmapBuffer;
import com.jayson.show.ui.customview.drawer.SystemParams;
import com.jayson.show.ui.customview.drawer.view.abs.ShapeDrawer;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：绘图区
 */
public class CusImageView extends View {

    private ShapeDrawer shapeDrawer;//图形绘制器

    public void setShapeDrawer(ShapeDrawer shapeDrawer) {
        this.shapeDrawer = shapeDrawer;
    }

    public CusImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //默认画矩形
        shapeDrawer = new RectDrawer(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        SystemParams.areaWidth = this.getMeasuredWidth();
        SystemParams.areaHeight = this.getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (SystemParams.isRedo) {
            //撤销
            canvas.drawBitmap(BitmapBuffer.getInstance().getBitmap(),
                    0, 0, null);
            SystemParams.isRedo = false;
        } else {
            shapeDrawer.draw(canvas);
        }
        //逻辑
        shapeDrawer.logic();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return shapeDrawer.onTouchEvent(event);
    }
}
