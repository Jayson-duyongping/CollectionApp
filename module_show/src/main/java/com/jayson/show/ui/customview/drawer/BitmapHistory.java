package com.jayson.show.ui.customview.drawer;

import android.graphics.Bitmap;

import java.util.Stack;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：绘图历史类(控制撤销)
 */
public class BitmapHistory {
    private static Stack<Bitmap> stack;
    private static BitmapHistory self;

    private BitmapHistory() {
        if (stack == null) {
            stack = new Stack<>();
        }
    }

    public static BitmapHistory getInstance() {
        if (self == null) {
            self = new BitmapHistory();
        }
        return self;
    }

    /**
     * 将当前的历史绘图结果压栈
     *
     * @param bitmap
     */
    public void pushBitmap(Bitmap bitmap) {
        int count = stack.size();
        //由于bitmap占用内存大，我们只存5次记录
        if (count >= 5) {
            Bitmap bmp = stack.remove(0);
            if (!bmp.isRecycled()) {
                bmp.recycle();
                System.gc();
                bmp = null;
            }
        }
        stack.push(bitmap);
    }

    /**
     * 撤销
     *
     * @return
     */
    public Bitmap reDo() {
        Bitmap bmp = stack.pop();//将顶部元素删除
        //回收位图资源
        if (bmp != null && !bmp.isRecycled()) {
            bmp.recycle();
            System.gc();
            bmp = null;
        }
        if (stack.empty()) {
            return null;
        }
        //返回撤销后的位图对象
        return stack.peek();
    }

    /**
     * 判断是否还能撤消
     *
     * @return
     */
    public boolean isReDo() {
        return !stack.empty();
    }

    /**
     * 清空
     */
    public void clear() {
        stack.clear();
    }
}
