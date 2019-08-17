package com.jayson.show.ui.customview.sgb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/10
 * 创建内容：扫描渐变
 */
public class SweepGradientView extends View {
    public SweepGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect rect = new Rect(0, 0, 500, 500);
        SweepGradient sg = new SweepGradient(
                250, 250, Color.RED, Color.GREEN
        );
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(sg);
        canvas.drawOval(new RectF(rect), paint);
        canvas.translate(510, 0);
        //多个颜色使它不至于突兀
        SweepGradient sg1 = new SweepGradient(
                250, 250, new int[]{Color.GREEN, Color.YELLOW, Color.RED, Color.GREEN}, null
        );
        paint.setShader(sg1);
        canvas.drawOval(new RectF(rect), paint);
    }
}
