package com.jayson.show.ui.customview.sgb;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/10
 * 创建内容：旋转的圆(可以制作光碟什么的)
 * 渐变与 Matrix
 * setLocalMatrix(Matrix localM)，该方法能和渐变结合，
 * 在填充渐变颜色的时候实现移位、旋转、缩放和拉斜的效果。
 */
public class SweepCircle extends View {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mRotate;
    private Matrix mMatrix = new Matrix();
    private Shader mShader;

    public SweepCircle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFocusable(true);
        setFocusableInTouchMode(true);

        float x = 160;
        float y = 100;
        mShader = new SweepGradient(x, y, new int[]{Color.GREEN,
                Color.RED,
                Color.BLUE,
                Color.GREEN}, null);
        mPaint.setShader(mShader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = mPaint;
        float x = 160;
        float y = 100;
        canvas.translate(300, 300);
        canvas.drawColor(Color.WHITE);
        //mMatrix 定义了以圆点为中心渐变的旋转效果，
        //注意不是旋转 Canvas 而是旋转 SweepGradient
        mMatrix.setRotate(mRotate, x, y);
        mShader.setLocalMatrix(mMatrix);
        mRotate += 3;
        if (mRotate >= 360) {
            mRotate = 0;
        }
        invalidate();
        canvas.drawCircle(x, y, 380, paint);
    }
}
