package com.jayson.show.ui.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.jayson.show.R;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/12
 * 创建内容：自定义一个TextView，使文字始终在组件中间
 * 自定义属性：
 * 1.定义自定义属性res/values/attr.xml
 * 2.在layout中使用自定义属性
 * 3.在组件类的构造方法中读取属性
 */
public class CenterTextView extends View {
    private String text = "";
    private Paint paint;

    public CenterTextView(Context context) {
        super(context);
    }

    public CenterTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //读取属性值
        TypedArray attr =
                context.obtainStyledAttributes(attrs, R.styleable.CenterTextView);
        text = attr.getString(R.styleable.CenterTextView_text);
        attr.recycle();
        if (text == null) {
            text = "";
        }
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(50);
        paint.setColor(Color.RED);
    }

    public CenterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 测量设置高度
     * 外层如果是ScrollView需要在xml中加android:fillViewport="true"
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec,
                             int heightMeasureSpec) {
        Rect rect = getTextRect();
        int textWidth = rect.width();
        int textHeight = rect.height();
        //测量组件宽高
        int width = measureWidth(widthMeasureSpec, textWidth);
        int height = measureHeight(heightMeasureSpec, textHeight);
        //设置宽高
        setMeasuredDimension(width, height);
    }

    /**
     * 测量组件宽度
     *
     * @param widthMeasureSpec
     * @param textWidth
     * @return
     */
    private int measureWidth(int widthMeasureSpec, int textWidth) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            //宽度为 match_parent 和具体值时，直接将 size 作为组件的宽度
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //宽度为 wrap_content，宽度需要计算，此处为文字宽度
            width = textWidth;
        }
        return width;
    }

    /**
     * 测量组件高度
     *
     * @param heightMeasureSpec
     * @param textHeight
     * @return
     */
    private int measureHeight(int heightMeasureSpec, int textHeight) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            //宽度为 match_parent 和具体值时，直接将 size 作为组件的宽度
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //宽度为 wrap_content，宽度需要计算，此处为文字宽度
            height = textHeight;
        }
        return height;
    }

    /**
     * 获取文字所占的尺寸
     *
     * @return
     */
    public Rect getTextRect() {
        //根据Paint设置的绘制参数计算文字所占的宽度
        Rect rect = new Rect();
        //文字所占的区域大小保存在rect中
        paint.getTextBounds(text, 0, text.length(), rect);
        return rect;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //将文字放在中间
        Rect textRect = this.getTextRect();
        int viewWidth = getMeasuredWidth();
        int viewHeight = getMeasuredHeight();
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int x = (viewWidth - textRect.width()) / 2;
        //int baseline=height/2+
        //(fontMetrics.descent-fontMetrics.ascent)/2-fontMetrics.descent
        int y = (int) ((viewHeight / 2)
                + (fontMetrics.descent - fontMetrics.ascent) / 2
                - fontMetrics.descent);
        canvas.drawText(text, x, y, paint);
    }
}
