package com.jayson.show.ui.customview.flow;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建人：jayson
 * 创建时间：2019/8/13
 * 创建内容：流式布局
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int n = getChildCount();
        //当前行的子组件的最大高度
        int maxViewHeight = 0;
        //当前行的子组件的总宽度
        int maxLineWidth = 0;
        //累计高度
        int totalHeight = 0;
        //容器宽度
        int width = getMeasuredWidth();
        for (int i = 0; i < n; i++) {
            View child = getChildAt(i);
            //判断是否要换行显示（已占宽度+当前子组件的宽度是否大于容器的宽度）
            if (maxLineWidth + child.getMeasuredWidth()
                    > width - getPaddingLeft() - getPaddingRight()) {
                //换行后累计已显示的行的总高度
                totalHeight += maxViewHeight;
                //新起一行，新行的已占宽度和高度重置为0
                maxLineWidth = 0;
                maxViewHeight = 0;
            }
            //定位子组件
            layoutChild(child, maxLineWidth, totalHeight,
                    maxLineWidth + child.getMeasuredWidth(),
                    totalHeight + child.getMeasuredHeight());
            //获取当前行的最高高度
            maxViewHeight = Math.max(maxViewHeight,
                    child.getMeasuredHeight());
            //累加当前行的宽度
            maxLineWidth += child.getMeasuredWidth();
        }
    }

    /**
     * 定位子组件，方法内考虑 padding
     *
     * @param child
     * @param l
     * @param t
     * @param r
     * @param b
     */
    private void layoutChild(View child, int l,
                             int t, int r, int b) {
        //所有子组件要统一向右和向下平移指定的padding
        child.layout(l + getPaddingLeft(), t + getPaddingTop(),
                r + getPaddingLeft(), b + getPaddingTop());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先测量子组件
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //再测量自身
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    /**
     * 测量容器宽度
     *
     * @param widthMeasureSpec
     * @return
     */
    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //计算出所有子组件所占的总宽度
            int n = getChildCount();
            int childrenWidth = 0;
            for (int i = 0; i < n; i++) {
                View child = getChildAt(i);
                int childWidth = child.getMeasuredWidth();
                //单个子组件的宽度不能超过容器宽度
                if (childWidth > size) {
                    throw new IllegalStateException("Sub view is too large!");
                }
                childrenWidth += childWidth;
            }
            //在wrap_content 的情况下，如果子组件占的总宽度>容器的
            //最大宽度，则应该取容器最大宽度
            if (childrenWidth > size) {
                width = size;
            } else {
                width = childrenWidth;
            }
        }
        //padding
        width += this.getPaddingLeft() + getPaddingRight();
        return width;
    }

    /**
     * 测量容器高度
     *
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //wrap_content容器高度跟随内容
            int width = getMeasuredWidth();
            int n = getChildCount();
            //当前行的子组件的最大高度
            int maxViewHeight = 0;
            //当前行的子组件的总宽度
            int maxLineWidth = 0;
            for (int i = 0; i < n; i++) {
                View child = getChildAt(i);
                maxLineWidth += child.getMeasuredWidth();
                maxViewHeight = Math.max(
                        child.getMeasuredHeight(), maxViewHeight);
                //预测是否需要换行，getChildAt(i+1)是将下一个子组件事先考虑进来
                if (i < n - 1 && maxLineWidth + getChildAt(i + 1).getMeasuredWidth()
                        > width - getPaddingLeft() - getPaddingRight()) {
                    //当前行的子组件宽度如果超出容器的宽度，则要换行
                    height += maxViewHeight;
                    maxViewHeight = 0;
                    maxLineWidth = 0;
                } else if (i == n - 1) {
                    //已经遍历到最后一个
                    height += maxViewHeight;
                }
            }
        }
        //padding
        height += getPaddingTop() + getPaddingBottom();
        return height;
    }
}
