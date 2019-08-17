package com.jayson.show.ui.customview.sgb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：线性渐变
 */
public class LinearGradientView extends View {

    private static final int OFFSET = 100;
    private Paint paint;

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect1 = new Rect(100, 100, 500, 400);
        //左上点到右下点的渐变
        LinearGradient lg = new LinearGradient(
                rect1.left, rect1.top, rect1.right, rect1.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(lg);
        canvas.drawRect(rect1, paint);

        //坐标往下移动
        canvas.translate(0, rect1.height() + OFFSET);
        //放大渐变矩形
        Rect rect2 = new Rect(rect1);
        rect2.inset(-100, -100);
        lg = new LinearGradient(
                rect2.left, rect2.top, rect2.right, rect2.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(lg);
        canvas.drawRect(rect1, paint);

        //坐标往下移动
        canvas.translate(0, rect1.height() + OFFSET);
        //缩小渐变矩形
        Rect rect3 = new Rect(rect1);
        rect3.inset(100, 100);
        lg = new LinearGradient(
                rect3.left, rect3.top, rect3.right, rect3.bottom,
                Color.RED, Color.BLUE, Shader.TileMode.CLAMP);
        paint.setShader(lg);
        canvas.drawRect(rect1, paint);
    }
}
