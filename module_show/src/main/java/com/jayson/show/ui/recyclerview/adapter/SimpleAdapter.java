package com.jayson.show.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jayson.show.R;
import com.jayson.show.ui.recyclerview.adapter.holder.TypeOneViewHolder;
import com.jayson.show.ui.recyclerview.adapter.holder.TypeThreeViewHolder;
import com.jayson.show.ui.recyclerview.adapter.holder.TypeTwoViewHolder;
import com.jayson.show.ui.recyclerview.adapter.holder.TypeViewHolder;
import com.jayson.show.ui.recyclerview.bean.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：jayson
 * 创建时间：2019/8/14
 * 创建内容：
 */
public class SimpleAdapter extends RecyclerView.Adapter<TypeViewHolder> {

    private LayoutInflater mInflater;

    private List<DataModel> mList = new ArrayList<>();

    public SimpleAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    public void addList(List<DataModel> list) {
        mList.addAll(list);
    }

    @NonNull
    @Override
    public TypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //根据类型创建ViewHolder
        switch (viewType) {
            case DataModel.TYPE_ONE:
                return new TypeOneViewHolder(mInflater.inflate(
                        R.layout.item_rv_one, parent, false));
            case DataModel.TYPE_TWO:
                return new TypeTwoViewHolder(mInflater.inflate(
                        R.layout.item_rv_two, parent, false));
            case DataModel.TYPE_THREE:
                return new TypeThreeViewHolder(mInflater.inflate(
                        R.layout.item_rv_three, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHolder viewHolder, int position) {
        viewHolder.bindModel(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mList.get(position).getType();
    }
}
