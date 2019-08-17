package com.jayson.show.ui.customview.viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建人：jayson
 * 创建时间：2019/8/13
 * 创建内容：四角容器
 * 要结合父容器padding，子组件margin来考虑
 */
public class CornerLayout extends ViewGroup {
    public CornerLayout(Context context) {
        super(context);
    }

    public CornerLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CornerLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int leftPadding = getPaddingLeft();
        int rightPadding = getPaddingRight();
        int topPadding = getPaddingTop();
        int bottomPadding = getPaddingBottom();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams)
                    child.getLayoutParams();
            int leftMargin = layoutParams.leftMargin;
            int rightMargin = layoutParams.rightMargin;
            int topMargin = layoutParams.topMargin;
            int bottomMargin = layoutParams.bottomMargin;
            if (i == 0) {
                //定位到左上角
                child.layout(leftPadding + leftMargin,
                        topPadding + topMargin,
                        child.getMeasuredWidth() + leftPadding + leftMargin,
                        child.getMeasuredHeight() + topPadding + topMargin);
            } else if (i == 1) {
                //定位到右上角
                child.layout(getMeasuredWidth() - child.getMeasuredWidth()
                                - rightPadding - rightMargin,
                        topPadding + rightMargin,
                        getMeasuredWidth() - rightPadding - rightMargin,
                        child.getMeasuredHeight() + topPadding + rightMargin);
            } else if (i == 2) {
                //定位到左下角
                child.layout(leftPadding + leftMargin,
                        getMeasuredHeight() - child.getMeasuredHeight()
                                - bottomPadding - bottomMargin,
                        child.getMeasuredWidth() + leftPadding + leftMargin,
                        getMeasuredHeight() - bottomPadding - bottomMargin);
            } else if (i == 3) {
                //定位到右下角
                child.layout(getMeasuredWidth() - child.getMeasuredWidth()
                                - rightPadding - rightMargin,
                        getMeasuredHeight() - child.getMeasuredHeight()
                                - bottomPadding - bottomMargin,
                        getMeasuredWidth() - rightPadding - rightMargin,
                        getMeasuredHeight() - bottomPadding - bottomMargin);
            }
        }
    }

    /**
     * 测量尺寸
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //先测量所有子组件的大小
        this.measureChildren(widthMeasureSpec, heightMeasureSpec);
        //再测量自己的大小
        int width = this.measureWidth(widthMeasureSpec);
        int height = this.measureHeight(heightMeasureSpec);
        //应用尺寸
        this.setMeasuredDimension(width, height);
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int width = 0;
        if (mode == MeasureSpec.EXACTLY) {
            //match_parent 或具体值
            width = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //wrap_content
            int aWidth = 0;
            int bWidth = 0;
            int cWidth = 0;
            int dWidth = 0;
            int marginHa, marginHb, marginHc, marginHd;
            marginHa = marginHb = marginHc = marginHd = 0;
            for (int i = 0; i < this.getChildCount(); i++) {
                MarginLayoutParams layoutParams = (MarginLayoutParams)
                        getChildAt(i).getLayoutParams();
                if (i == 0) {
                    aWidth = getChildAt(i).getMeasuredWidth();
                    marginHa += layoutParams.leftMargin + layoutParams.rightMargin;
                } else if (i == 1) {
                    bWidth = getChildAt(i).getMeasuredWidth();
                    marginHb += layoutParams.leftMargin + layoutParams.rightMargin;
                } else if (i == 2) {
                    cWidth = getChildAt(i).getMeasuredWidth();
                    marginHc += layoutParams.leftMargin + layoutParams.rightMargin;
                } else if (i == 3) {
                    dWidth = getChildAt(i).getMeasuredWidth();
                    marginHd += layoutParams.leftMargin + layoutParams.rightMargin;
                }

            }
            //宽度 =	A、C 的最大宽度+B、D 的最大宽度+容器左边的 padding+容器右边的padding
            //+A、C左、右的最大margin+B、D 左、右的最大margin；
            width = Math.max(aWidth, cWidth) + Math.max(bWidth, dWidth)
                    + getPaddingLeft() + getPaddingRight()
                    + Math.max(marginHa, marginHc)
                    + Math.max(marginHb, marginHd);
        }
        return width;
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int height = 0;
        if (mode == MeasureSpec.EXACTLY) {
            //match_parent 或具体值
            height = size;
        } else if (mode == MeasureSpec.AT_MOST) {
            //wrap_content
            int aHeight = 0;
            int bHeight = 0;
            int cHeight = 0;
            int dHeight = 0;
            int marginVa, marginVb, marginVc, marginVd;
            marginVa = marginVb = marginVc = marginVd = 0;
            for (int i = 0; i < this.getChildCount(); i++) {
                MarginLayoutParams layoutParams = (MarginLayoutParams)
                        getChildAt(i).getLayoutParams();
                if (i == 0) {
                    aHeight = getChildAt(i).getMeasuredHeight();
                    marginVa += layoutParams.topMargin +
                            layoutParams.bottomMargin;
                } else if (i == 1) {
                    bHeight = getChildAt(i).getMeasuredHeight();
                    marginVb += layoutParams.topMargin +
                            layoutParams.bottomMargin;
                } else if (i == 2) {
                    cHeight = getChildAt(i).getMeasuredHeight();
                    marginVc += layoutParams.topMargin +
                            layoutParams.bottomMargin;
                } else if (i == 3) {
                    dHeight = getChildAt(i).getMeasuredHeight();
                    marginVd += layoutParams.topMargin +
                            layoutParams.bottomMargin;
                }

            }
            //高度=A、B的最大高度+C、D的最大高度+容器顶部的padding+容器底部的padding +
            //A、B 顶、底部的最大margin+C、D顶、底部的最大margin。
            height = Math.max(aHeight, bHeight) + Math.max(cHeight, dHeight)
                    + getPaddingTop() + getPaddingBottom()
                    + Math.max(marginVa, marginVb)
                    + Math.max(marginVc, marginVd);
        }
        return height;
    }

    /**
     * 为了让CornerLayout支持margin特征
     * 需要重写：
     * generateDefaultLayoutParams()
     * generateLayoutParams(AttributeSet attrs)
     * generateLayoutParams(LayoutParams p)
     *
     * @param attrs
     * @return
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(this.getContext(), attrs);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
    }
}
