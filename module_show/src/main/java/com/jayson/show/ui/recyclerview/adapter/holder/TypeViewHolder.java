package com.jayson.show.ui.recyclerview.adapter.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建人：jayson
 * 创建时间：2019/8/14
 * 创建内容：
 */
public abstract class TypeViewHolder<T> extends RecyclerView.ViewHolder {

    public TypeViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindModel(T model);
}
