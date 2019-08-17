package com.jayson.show.ui.customview.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.jayson.show.R;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/12
 * 创建内容：圆形 ImageView 组件
 */
@SuppressLint("AppCompatCustomView")
public class CircleImageView extends AppCompatImageView {

    private Paint paint;
    private Xfermode xfermode;
    private Path path;
    private int border;
    private int borderColor;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        //位图运算模式
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

        path = new Path();
        //获取属性
        TypedArray attr =
                context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        border = (int) attr.getDimension(
                R.styleable.CircleImageView_circle_border, 0);
        borderColor = attr.getColor(
                R.styleable.CircleImageView_circle_border_color, Color.GRAY);
        attr.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取ImageView设置的图片
        Drawable mDrawable = getDrawable();
        if (mDrawable == null) {
            super.onDraw(canvas);
        }
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        //需要一个实心椭圆作为遮罩层-内切椭圆
        RectF ovalRect = new RectF(0, 0, width, height);
        //因为要位图运算，先创建一个图层
        int layerId = canvas.saveLayer(getPaddingLeft(), getPaddingTop(),
                width, height, null, Canvas.ALL_SAVE_FLAG);
        Bitmap bitmap = ((BitmapDrawable) mDrawable).getBitmap();
        //将图片绘制到canvas画布并进行缩放
        canvas.drawBitmap(bitmap, new Rect(0, 0,
                mDrawable.getIntrinsicWidth(),
                mDrawable.getIntrinsicHeight()), ovalRect, null);
        //指定画笔位图模式
        paint.setXfermode(xfermode);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        path.reset();
        //在Path对象中添加一个椭圆
        path.addOval(ovalRect, Path.Direction.CCW);
        //进行位图运算-只显示DST
        //只有Path和Bitmap对象才能进行位图运算，不能直接调用 drawOval()方法
        canvas.drawPath(path, paint);
        //清空恢复图层
        paint.setXfermode(null);
        canvas.restoreToCount(layerId);

        //画空心圆
        if (border != 0) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(borderColor);
            paint.setStrokeWidth(border);
            ovalRect.inset(border / 2, border / 2);
            canvas.drawOval(ovalRect, paint);
        }
    }
}
