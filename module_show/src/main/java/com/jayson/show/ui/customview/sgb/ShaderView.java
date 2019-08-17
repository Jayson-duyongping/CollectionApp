package com.jayson.show.ui.customview.sgb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：实现为文字添加阴影和发光的效果
 */
public class ShaderView extends View {
    public ShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(100);
        //指定层的阴影类型
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        paint.setShadowLayer(10, 1, 1, Color.RED);
        canvas.drawText("Android开发", 100, 100, paint);
        paint.setShadowLayer(10, 5, 5, Color.BLUE);
        canvas.drawText("Android绘图技术", 100, 220, paint);
    }
}
