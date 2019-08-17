package com.jayson.show.ui.customview.viewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 创建人：jayson
 * 创建时间：2019/8/12
 * 创建内容：自定义容器
 */
public class SizeViewGroup extends ViewGroup {
    public SizeViewGroup(Context context) {
        this(context, null);
    }

    public SizeViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SizeViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //创建一个按钮
        TextView textView = new TextView(context);
        ViewGroup.LayoutParams lp
                = new LayoutParams(200, 200);
        textView.setText("Android");
        textView.setBackgroundColor(Color.YELLOW);
        //在当前容器中添加子组件
        this.addView(textView, lp);
        //设置容器背景颜色
        this.setBackgroundColor(Color.alpha(255));
    }

    /**
     * 确定组件位置与大小
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //设置子组件的位置和大小
        View textView = this.getChildAt(0);
        //这里只有一个子组件
        textView.layout(50, 50, textView.getMeasuredWidth() + 50,
                textView.getMeasuredHeight() + 50);
    }

    /**
     * 测量组件
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        //先测量所有子组件的大小
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        //测量自身的大小，此处直接写死500，500
        this.setMeasuredDimension(500, 500);
    }

    /**
     * 绘制
     */
    @Override
    protected void onDraw(Canvas canvas) {
        //为容器画一个红色边框
        RectF rect = new RectF(0, 0,
                getMeasuredWidth(), getMeasuredHeight());
        rect.inset(2, 2);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        Path path = new Path();
        path.addRoundRect(rect, 20, 20, Path.Direction.CCW);
        canvas.drawPath(path, paint);
        super.onDraw(canvas);
    }
}
