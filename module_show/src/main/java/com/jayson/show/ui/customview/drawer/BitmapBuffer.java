package com.jayson.show.ui.customview.drawer;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * 创建人：jayson
 * 创建时间：2019/8/9
 * 创建内容：定义了绘图缓冲区，提供了“双缓存”中的 Bitmap 对象
 */
public class BitmapBuffer {
    private Bitmap bitmap;
    private Canvas canvas;
    private static BitmapBuffer self;

    private BitmapBuffer(int width, int height) {
        init(width, height);
    }

    public static BitmapBuffer getInstance() {
        if (self == null) {
            self = new BitmapBuffer(
                    SystemParams.areaWidth, SystemParams.areaHeight);
        }
        return self;
    }

    /**
     * 初始化
     *
     * @param width
     * @param height
     */
    private void init(int width, int height) {
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        canvas = new Canvas();
        canvas.setBitmap(bitmap);
    }

    /**
     * 获得绘图结果
     *
     * @return
     */
    public Bitmap getBitmap() {
        return bitmap;
    }

    /**
     * 获取缓冲区的画布
     *
     * @return
     */
    public Canvas getCanvas() {
        return canvas;
    }

    /**
     * 将当前的绘图结果保存到栈中
     */
    public void pushBitmap() {
        BitmapHistory his = BitmapHistory.getInstance();
        his.pushBitmap(
                bitmap.copy(Bitmap.Config.ARGB_8888, false));
    }

    /**
     * 撤销
     */
    public void redo() {
        BitmapHistory his = BitmapHistory.getInstance();
        if (his.isReDo()) {
            Bitmap bmp = his.reDo();
            if (bmp != null) {
                bitmap = bmp.copy(Bitmap.Config.ARGB_8888, true);
                //必须重新关联画布
                canvas.setBitmap(bitmap);
                //回收
                if (bmp != null && !bmp.isRecycled()) {
                    bmp.recycle();
                    System.gc();
                    bmp = null;
                }
            }else{
                //初始化
                init(SystemParams.areaWidth, SystemParams.areaHeight);
            }
        }
    }
}
