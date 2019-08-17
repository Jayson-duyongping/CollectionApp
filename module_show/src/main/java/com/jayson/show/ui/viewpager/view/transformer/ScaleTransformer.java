package com.jayson.show.ui.viewpager.view.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 创建人：jayson
 * 创建时间：2019/8/6
 * 创建内容：ViewPager滑动动画,实现PageTransformer
 */
public class ScaleTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.75f;
    private static final float MIN_ALPHA = 0.5f;

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
        if (position < -1) {
            //[,-1]
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
        } else if (position <= 1) {
            //[-1,1]
            if (position < 0) {
                //左边页面
                //页面滑动：a->b (0,-1);缩放值：1->0.75
                //页面滑动：b->a (-1,0);缩放值：0.75->1
                float scaleA = MIN_SCALE + (1 - MIN_SCALE) * (1 + position);
                page.setScaleX(scaleA);
                page.setScaleY(scaleA);
                float alpha = MIN_ALPHA + (1 - MIN_ALPHA) * (1 + position);
                page.setAlpha(alpha);
            } else {
                //右边页面
                //页面滑动：a->b (1,0);缩放值：0.75->1
                //页面滑动：b->a (0,1);缩放值：1->0.75
                float scaleB = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);
                page.setScaleX(scaleB);
                page.setScaleY(scaleB);
                float alpha = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - position);
                page.setAlpha(alpha);
            }
        } else {
            //[1,]
            page.setScaleX(MIN_SCALE);
            page.setScaleY(MIN_SCALE);
            page.setAlpha(MIN_ALPHA);
        }
    }
}
