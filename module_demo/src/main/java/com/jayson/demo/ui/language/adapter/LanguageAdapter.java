package com.jayson.demo.ui.language.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jayson.demo.R;
import com.jayson.demo.ui.language.bean.LanguageRegion;
import com.jayson.demo.ui.language.utils.LanguageUtils;

import java.util.List;

/**
 * 创建人：jayson
 * 创建时间：2019/8/25
 * 创建内容：
 */
public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.Holder> {

    private LayoutInflater mInflater;
    private List<LanguageRegion> regions;
    private Context context;

    private onItemClickListener onItemClickListener;

    public interface onItemClickListener {
        void onItemClick(LanguageRegion region);
    }

    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public LanguageAdapter(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        regions = LanguageUtils.getLanguageList();
        //判断当前的语言区域
        LanguageRegion currentRegion = LanguageUtils.getLanguage(context.getApplicationContext());
        for (LanguageRegion r : regions) {
            r.setSelected(false);
            if (r.getDisplayCountry().equals(currentRegion.getDisplayCountry())) {
                r.setSelected(true);
            }
        }
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_language, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        LanguageRegion region = regions.get(position);
        holder.languageTv.setText(
                region.getDisplayLanguage() + "(" + region.getDisplayCountry() + ")");
        holder.checkBox.setVisibility(region.isSelected() ? View.VISIBLE : View.INVISIBLE);
        holder.languageLl.setOnClickListener(v -> {
            for (LanguageRegion r : regions) {
                r.setSelected(false);
                if (r.getDisplayLanguage().equals(region.getDisplayLanguage())) {
                    r.setSelected(true);
                    onItemClickListener.onItemClick(region);
                }
            }
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;
        private TextView languageTv;
        private LinearLayout languageLl;

        public Holder(@NonNull View itemView) {
            super(itemView);
            languageLl = itemView.findViewById(R.id.language_ll);
            checkBox = itemView.findViewById(R.id.checkbox);
            languageTv = itemView.findViewById(R.id.language_tv);
        }
    }
}
