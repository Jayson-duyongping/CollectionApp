package com.jayson.show.ui.customview.sgb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.jayson.show.R;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/10
 * 创建内容：圆形头像
 * 解决思路:
 * 将照片作为 DST，SRC 则是新创建的画了实心圆的位图，其实也是遮罩层。
 * 如果既要显示出 SRC 的形状，又要显示出 DST 的内容，则必须使用 DST_IN 运
 * 算模式（DST 表示显示 DST 内容，IN 表示只显示相交部分）
 */
public class Ex1_CirclePhotoView extends View {
    private Bitmap bmpHead;
    private Bitmap bmpCircleMask;
    private Canvas cvsCircle;
    private Paint paint;

    public Ex1_CirclePhotoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bmpHead = BitmapFactory.decodeResource(getResources(), R.mipmap.xiaowu);
        int minWidth = Math.min(bmpHead.getWidth(), bmpHead.getHeight());
        bmpCircleMask = Bitmap.createBitmap(
                minWidth, minWidth, Bitmap.Config.ARGB_8888);
        //画一个实心圆放入bitmap
        cvsCircle = new Canvas(bmpCircleMask);
        int r = minWidth / 2;
        cvsCircle.drawCircle(r, r, r, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //新建图层,大小位置以bmpCircleMask为准
        int w = bmpCircleMask.getWidth();
        int layer = canvas.saveLayer(0, 0, w, w, null, Canvas.ALL_SAVE_FLAG);

        //绘制素材位图
        canvas.drawBitmap(bmpHead, 0, 0, null);
        //指定位图运算模式(取两层交集部分，交集内容取决于下层)
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //绘制圆模板
        canvas.drawBitmap(bmpCircleMask, 0, 0, paint);

        //恢复图层
        canvas.restoreToCount(layer);
    }
}
