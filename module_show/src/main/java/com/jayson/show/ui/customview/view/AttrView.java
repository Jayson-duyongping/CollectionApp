package com.jayson.show.ui.customview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.jayson.show.R;
import com.jayson.show.utils.L;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/12
 * 创建内容：读取属性的测试
 */
public class AttrView extends View {
    public AttrView(Context context) {
        super(context);
    }

    public AttrView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.myStyle);
    }

    public AttrView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * obtainStyledAttributes(AttributeSet set,int[] attrs,
         * int defStyleAttr,int defStyleRes)
         * set:属性值的集合
         * attrs:R.styleable.AttrView 数组
         * defStyleAttr:当前Theme中style属性
         * defStyleRes:指向一个Style的资源 ID，
         * 以上会根据优先级一层一层读取
         */
        TypedArray attr = context.obtainStyledAttributes(
                attrs, R.styleable.AttrView,
                defStyleAttr, R.style.myDefaultStyle);
        String attr1 = attr.getString(R.styleable.AttrView_attr1);
        String attr2 = attr.getString(R.styleable.AttrView_attr2);
        String attr3 = attr.getString(R.styleable.AttrView_attr3);
        String attr4 = attr.getString(R.styleable.AttrView_attr4);
        L.d(attr1 + "");
        L.d(attr2 + "");
        L.d(attr3 + "");
        L.d(attr4 + "");
    }
}
