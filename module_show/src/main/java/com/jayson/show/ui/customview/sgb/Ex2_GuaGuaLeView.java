package com.jayson.show.ui.customview.sgb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jayson.show.R;

import java.util.Random;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/10
 * 创建内容：刮刮乐
 * 1.绘制背景
 * 2.在屏幕上绘制线条
 */
public class Ex2_GuaGuaLeView extends View {

    private Random rnd;
    //绘制背景的画笔
    private Paint paint;
    //手指涂抹的画笔
    private Paint clearPaint;
    private static final String[] PRIZE = {
            "恭喜，您中了一等奖，奖金 5000 万元",
            "恭喜，您中了二等奖，奖金 100 万元",
            "恭喜，您中了三等奖，奖金 10 万元",
            "很遗憾，您没有中奖，继续加油哦"
    };
    //涂抹的粗细
    private static final int FINGER = 50;
    //缓冲区
    private Bitmap bmpBuffer;
    //缓冲区画布
    private Canvas cvsBuffer;
    private int curX, curY;

    public Ex2_GuaGuaLeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rnd = new Random();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(40);
        paint.setColor(Color.WHITE);

        clearPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //将线条与灰色蒙层做Mode.CLEAR 运算，相交的部分即被清除，变成了透明效果
        clearPaint.setXfermode(
                new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        clearPaint.setStrokeJoin(Paint.Join.ROUND);
        clearPaint.setStrokeCap(Paint.Cap.ROUND);
        clearPaint.setStrokeWidth(FINGER);
        //画背景
        drawBackground();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //初始化缓冲区
        bmpBuffer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cvsBuffer = new Canvas(bmpBuffer);
        //为缓冲区蒙上一层灰色
        cvsBuffer.drawColor(Color.parseColor("#FF808080"));
    }

    /**
     * 绘制背景，背景包括背景图片和中奖信息
     */
    private void drawBackground() {
        Bitmap bmpBackGround = BitmapFactory.decodeResource(
                getResources(), R.mipmap.prize);
        //从资源中读取的bmpBackGround不可以修改，复制出一张可以修复的图片
        Bitmap bmpBackGroundMutable = bmpBackGround.copy(
                Bitmap.Config.ARGB_8888, true);
        //在图片上画上中奖信息
        Canvas cvsBackGround = new Canvas(bmpBackGroundMutable);
        //计算出文字所占的区域，将文字放在正中间
        String text = PRIZE[getPrizeIndex()];
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(), rect);
        int x = (bmpBackGroundMutable.getWidth() - rect.width()) / 2;
        int y = (bmpBackGroundMutable.getHeight() - rect.height()) / 2;
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
        paint.setShadowLayer(10, 0, 0, Color.GREEN);
        cvsBackGround.drawText(text, x, y, paint);
        paint.setShadowLayer(0, 0, 0, Color.YELLOW);
        //画背景
        this.setBackground(new BitmapDrawable(
                getResources(), bmpBackGroundMutable));
    }

    /**
     * 随机生成中奖信息
     *
     * @return 数组 PRIZE 的索引
     */
    private int getPrizeIndex() {
        return rnd.nextInt(PRIZE.length);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bmpBuffer, 0, 0, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                curX = x;
                curY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                cvsBuffer.drawLine(curX, curY, x, y, clearPaint);
                invalidate();
                curX = x;
                curY = y;
                break;
            case MotionEvent.ACTION_UP:
                invalidate();

                break;
        }
        return true;
    }
}
