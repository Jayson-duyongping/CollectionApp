package com.jayson.show.ui.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.jayson.show.R;

import java.util.Random;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/12
 * 创建内容：验证码组件 CodeView
 */
public class CodeView extends View {

    private int count;//验证码的数字个数
    private int lineCount;//干扰线的条数
    private int fontSize;//字体大小
    private int color;//字体颜色

    private String code;//验证码
    private Random rnd;
    private Paint paint;

    private static final int DEFAULT_COUNT = 4;
    private static final int DEFAULT_LINE_COUNT = 50;
    private static final int DEFAULT_FONT_SIZE = 12;//sp
    private static final int DEFAULT_COLOR = Color.BLACK;

    public CodeView(Context context) {
        super(context);
    }

    public CodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取属性
        TypedArray attr = context
                .obtainStyledAttributes(attrs, R.styleable.CodeView);
        count = attr.getInteger(R.styleable.CodeView_count, DEFAULT_COUNT);
        lineCount = attr.getInteger(R.styleable.CodeView_line_count, DEFAULT_LINE_COUNT);
        //字体大小getDimensionPixelSize()方法读取的值是像素，我们要转换为sp
        fontSize = attr.getDimensionPixelSize(
                R.styleable.CodeView_font_size,
                (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP,
                        DEFAULT_FONT_SIZE,
                        getResources().getDisplayMetrics()));
        color = attr.getColor(R.styleable.CodeView_code_color,
                DEFAULT_COLOR);
        attr.recycle();
        //初始化
        rnd = new Random();
        paint = new Paint();
        //初始化画笔
        initPaint();
        //初始化生成一个验证码
        code = getCode();
    }

    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(color);
        paint.setTextSize(fontSize);
    }

    /**
     * 生成验证码
     *
     * @return
     */
    private String getCode() {
        String str = "";
        for (int i = 0; i < count; i++) {
            str += rnd.nextInt(10);
        }
        return str;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        Rect textRect = getTextRect();
        int width = this.measureWidth(widthMeasureSpec, textRect);
        int height = this.measureHeight(heightMeasureSpec, textRect);
        setMeasuredDimension(width, height);
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
        paint.getTextBounds(code, 0, code.length(), rect);
        return rect;
    }

    /**
     * 计算组件宽度
     *
     * @param widthMeasureSpec
     * @param textRect
     * @return
     */
    private int measureWidth(int widthMeasureSpec, Rect textRect) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            width = getPaddingLeft() + textRect.width() + getPaddingRight();
        }
        return width;
    }

    /**
     * 计算组件高度
     *
     * @param heightMeasureSpec
     * @param textRect
     * @return
     */
    private int measureHeight(int heightMeasureSpec, Rect textRect) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            height = getPaddingTop() + textRect.height() + getPaddingBottom();
        }
        return height;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        Rect rect = new Rect(0, 0, width, height);
        //绘制外围矩形框
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);
        Rect rect1 = new Rect(rect);
        rect.inset(2, 2);//缩小一点
        canvas.drawRect(rect1, paint);
        //绘制随机干扰线
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.GRAY);
        for (int i = 0; i < lineCount; i++) {
            int x1 = rnd.nextInt(width);
            int y1 = rnd.nextInt(height);
            int x2 = rnd.nextInt(width);
            int y2 = rnd.nextInt(height);
            canvas.drawLine(x1, y1, x2, y2, paint);
        }
        //绘制验证码
        paint.setColor(color);
        Rect textRect = getTextRect();
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int x = (width - textRect.width()) / 2;
        int y = (int) (height / 2 +
                (fontMetrics.descent - fontMetrics.ascent) / 2
                - fontMetrics.descent);
        canvas.drawText(code, x, y, paint);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        code = getCode();
        //重新调整布局，依次调用onMeasure()和 onDraw()
        requestLayout();
    }

    public int getLineCount() {
        return lineCount;
    }

    public void setLineCount(int lineCount) {
        this.lineCount = lineCount;
        //重绘
        invalidate();
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, fontSize,
                getResources().getDisplayMetrics());
        initPaint();
        //重新调整布局大小
        requestLayout();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        initPaint();
        //重绘
        invalidate();
    }

    /**
     * 刷新验证码
     */
    public void refresh() {
        code = getCode();
        invalidate();
    }

    public String theCode() {
        return code;
    }
}
