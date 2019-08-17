package com.jayson.show.ui.customview.sgb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/10
 * 创建内容：
 * 位图的混合运算:
 * 1准备好分别代表 DST 和 SRC 的位图，同时准备第三个位图，该位图用于绘制 DST 和
 * SRC 运算后的结果；
 * 2创建大小合适的图层（layer）并入栈；
 * 3先将 DST 位图绘制在第三个位图上；
 * 4调用 Paint 的 setXfermode()方法定义位图运算模式；
 * 5再将 SRC 位图绘制在第三个位图上；
 * 6清除位图运算模式；
 * 7图层（layer）出栈
 * 8将第三个位图绘制在 View 的 Canvas 上以便显示
 */
public class PorterDuffXferView extends View {
    public PorterDuffXferView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //定义三张位图，一个源，一个目标，一个展示合成结果
        Bitmap dst = Bitmap.createBitmap(
                300, 300, Bitmap.Config.ARGB_8888);
        Bitmap src = dst.copy(Bitmap.Config.ARGB_8888, true);
        Bitmap b3 = Bitmap.createBitmap(
                450, 450, Bitmap.Config.ARGB_8888);
        //分别定义画布
        Canvas c1 = new Canvas(dst);
        Canvas c2 = new Canvas(src);
        Canvas c3 = new Canvas(b3);

        //在dst画一个圆
        Paint p1 = new Paint();
        p1.setColor(Color.GRAY);
        c1.drawCircle(150, 150, 150, p1);
        //在src画一个矩形
        Paint p2 = new Paint();
        p2.setColor(Color.GREEN);
        c2.drawRect(0, 0, 300, 300, p2);

        //定义画笔
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        /**创建图层(图层 layer 的位置和大小要根据实际情况进行设置)*/
        //这里只显示SRC
        int layer = c3.saveLayer(
                150, 150, 450, 450,
                null, Canvas.ALL_SAVE_FLAG);
        //在b3上先画dst
        c3.drawBitmap(dst, 0, 0, null);
        //定义位图运算模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
        //在b3上画src,并加上运算模式
        c3.drawBitmap(src, 150, 150, paint);
        //清除运算效果
        paint.setXfermode(null);
        /**恢复*/
        c3.restoreToCount(layer);
        //绘制到Canvas上
        canvas.drawBitmap(b3, 0, 0, null);
    }
}
