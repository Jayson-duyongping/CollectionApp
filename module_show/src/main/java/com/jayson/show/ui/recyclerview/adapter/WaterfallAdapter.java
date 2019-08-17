package com.jayson.show.ui.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jayson.show.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：jayson
 * 创建时间：2019/8/15
 * 创建内容：
 */
public class WaterfallAdapter extends RecyclerView.Adapter<WaterfallAdapter.MyViewHolder> {

    private LayoutInflater mInflater;
    private Context context;
    private List<String> mDatas;

    //每个item的高度
    private List<Integer> mHeights;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public WaterfallAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        this.mInflater = LayoutInflater.from(context);
        //动态随机生成每个item的高度
        mHeights = new ArrayList<>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (100 + Math.random() * 300));
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.item_fall, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        //设置动态高度
        if (position < mHeights.size()) {
            lp.height = mHeights.get(position);
            holder.itemView.setLayoutParams(lp);
        }
        holder.nameTv.setText(mDatas.get(position));
        //点击事件
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                //新增之后，position可能没有变化，我们直接取getLayoutPosition
                int layoutPosition = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView, layoutPosition);
            }
        });
        //长按事件
        holder.itemView.setOnLongClickListener(v -> {
            if (mOnItemClickListener != null) {
                int layoutPosition = holder.getLayoutPosition();
                mOnItemClickListener.onItemLongClick(holder.itemView, layoutPosition);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 新增
     *
     * @param pos
     */
    public void addData(int pos) {
        mDatas.add(pos, "Insert One");
        //就会有默认动画效果
        notifyItemInserted(pos);
    }

    /**
     * 移除
     *
     * @param pos
     */
    public void removeData(int pos) {
        mDatas.remove(pos);
        //就会有默认动画效果
        notifyItemRemoved(pos);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.name_tv);
        }
    }
}
