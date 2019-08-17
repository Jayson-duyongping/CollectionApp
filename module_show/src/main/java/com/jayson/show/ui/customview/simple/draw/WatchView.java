package com.jayson.show.ui.customview.simple.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：指针走动的手表
 * 1.绘制表盘周围的刻度
 * 2.绘制指针
 * 3.定义Timer定时器
 */
public class WatchView extends View {

    private Paint paint;
    private Calendar calendar;

    public WatchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        calendar = Calendar.getInstance();
    }

    public void run() {
        //定时器每秒重绘
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        }, 0, 1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取组件宽度
        int width = getMeasuredWidth();
        //获取组件高度
        int height = getMeasuredHeight();
        //计算圆盘直径，取短
        int len = Math.min(width, height);
        //绘制表盘
        drawPlate(canvas, len);
        //绘制指针
        drawPoints(canvas, len);
    }

    /**
     * 绘制表盘
     *
     * @param canvas
     * @param len
     */
    private void drawPlate(Canvas canvas, int len) {
        canvas.save();
        //画圆
        int r = len / 2;
        canvas.drawCircle(r, r, r, paint);
        //画刻度(一共60根)
        for (int i = 0; i < 60; i++) {
            //一长四短
            if (i % 5 == 0) {
                //长刻度占圆半径的1/10
                paint.setColor(Color.RED);
                paint.setStrokeWidth(4);
                canvas.drawLine(r + 9 * r / 10, r, len, r, paint);
            } else {
                //短刻度占圆半径的1/15
                paint.setColor(Color.GRAY);
                paint.setStrokeWidth(1);
                canvas.drawLine(r + 14 * r / 15, r, len, r, paint);
            }
            //以（r，r）为中心，将画布旋转6度
            canvas.rotate(6, r, r);
        }
        canvas.restore();
    }

    /**
     * 绘制指针
     *
     * @param canvas
     * @param len
     */
    private void drawPoints(Canvas canvas, int len) {
        //先获取系统时间
        calendar.setTimeInMillis(System.currentTimeMillis());
        //获取时分秒
        int hours = calendar.get(Calendar.HOUR) % 12;//转换为12小时制
        int minutes = calendar.get(Calendar.MINUTE);
        int seconds = calendar.get(Calendar.SECOND);
        //画时针
        //角度（顺时针）
        int degree = 360 / 12 * hours;
        //转换成弧度
        double radians = Math.toRadians(degree);
        //根据当前时计算时针两个点的坐标
        //起点（圆心点），终点：计算得到
        int r = len / 2;
        int startX = r;
        int startY = r;
        //已知圆的中心点坐标（x0、y0），圆的半径 r，那么圆上任何角度α对应的点的坐标
        //公式为：
        //x	=x0 + r*cosα
        //y	=y0 + r*sinα
        int endX = (int) (startX + 0.5 * r * Math.cos(radians));
        int endY = (int) (startY + 0.5 * r * Math.sin(radians));
        canvas.save();
        paint.setStrokeWidth(3);
        //0度从3点处开始，时间从12点处开始，所以需要将画布旋转90度
        canvas.rotate(-90, r, r);
        //画时针
        canvas.drawLine(startX, startY, endX, endY, paint);
        canvas.restore();

        //画分针
        //计算角度和弧度
        degree = 360 / 60 * minutes;
        radians = Math.toRadians(degree);
        //计算终点
        endX = (int) (startX + 0.6 * r * Math.cos(radians));
        endY = (int) (startY + 0.6 * r * Math.sin(radians));
        canvas.save();
        paint.setStrokeWidth(2);
        canvas.rotate(-90, r, r);
        canvas.drawLine(startX, startY, endX, endY, paint);
        canvas.restore();

        //画秒针
        //计算角度和弧度
        degree = 360 / 60 * seconds;
        radians = Math.toRadians(degree);
        //计算终点
        endX = (int) (startX + 0.8 * r * Math.cos(radians));
        endY = (int) (startY + 0.8 * r * Math.sin(radians));
        canvas.save();
        paint.setStrokeWidth(1);
        canvas.rotate(-90, r, r);
        canvas.drawLine(startX, startY, endX, endY, paint);
        //再给秒针画个“尾巴”
        radians = Math.toRadians(degree - 180);
        endX = (int) (startX + 0.15 * r * Math.cos(radians));
        endY = (int) (startY + 0.15 * r * Math.sin(radians));
        canvas.drawLine(startX, startY, endX, endY, paint);
        canvas.restore();
    }
}
