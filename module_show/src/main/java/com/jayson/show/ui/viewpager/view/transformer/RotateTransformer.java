package com.jayson.show.ui.viewpager.view.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 创建人：jayson
 * 创建时间：2019/8/6
 * 创建内容：ViewPager滑动动画-旋转动画
 */
public class RotateTransformer implements ViewPager.PageTransformer {

    //定义旋转放方向，1向上,2向下
    private int rotateDirect;
    //最大旋转角度
    private int MAX_ROTATE;

    public RotateTransformer(int rotateDirect) {
        this.rotateDirect = rotateDirect;
        if (this.rotateDirect == 1) {
            MAX_ROTATE = -15;
        } else {
            MAX_ROTATE = 15;
        }
    }

    /**
     * 关键是position的变化
     *
     * @param page
     * @param position
     */
    @Override
    public void transformPage(@NonNull View page, float position) {
        //a-b
        //a,position:(0,-1);b,position:(1,0)
        //b-a
        //a,position:(-1,0);b,position:(0,1)
        int pivotY;
        if (this.rotateDirect == 1) {
            pivotY = 0;
        } else {
            pivotY = page.getHeight();
        }
        if (position < -1) {
            //[,-1]
            page.setRotation(-MAX_ROTATE);
            page.setPivotY(pivotY);
            page.setPivotX(page.getWidth());
        } else if (position <= 1) {
            //[-1,1]
            if (position < 0) {
                //左边页面（先定中心，在定角度）
                page.setPivotY(pivotY);
                //0.5w->w
                page.setPivotX(0.5f * page.getWidth()
                        + 0.5f * (-position) * page.getWidth());
                //(0,-1)-> 0,-max
                page.setRotation(MAX_ROTATE * position);
            } else {
                //右边页面
                page.setPivotY(pivotY);
                //0,0.5w
                page.setPivotX(0.5f * page.getWidth() * (1 - position));
                //MAX,0
                page.setRotation(MAX_ROTATE * position);
            }
        } else {
            //[1,]
            page.setRotation(MAX_ROTATE);
            page.setPivotY(pivotY);
            page.setPivotX(0);
        }
    }
}
