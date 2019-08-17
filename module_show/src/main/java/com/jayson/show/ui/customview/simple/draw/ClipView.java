package com.jayson.show.ui.customview.simple.draw;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.jayson.show.R;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：剪切区
 */
public class ClipView extends View {
    public ClipView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //从资源中读取一张位图
        Bitmap bitmap = BitmapFactory
                .decodeResource(getResources(), R.mipmap.xiaowu);
        //绘制完整图片
        canvas.drawBitmap(bitmap, 0, 0, null);
        //平移坐标
        canvas.translate(0, 450);
        //定义剪切区
        canvas.clipRect(new Rect(300, 50, 700, 500));
        //再次绘制
        canvas.drawBitmap(bitmap, 0, 0, null);
    }
}
