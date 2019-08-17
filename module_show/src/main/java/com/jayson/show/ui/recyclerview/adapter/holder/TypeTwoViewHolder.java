package com.jayson.show.ui.recyclerview.adapter.holder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayson.show.R;
import com.jayson.show.ui.recyclerview.bean.DataModel;

/**
 * 创建人：jayson
 * 创建时间：2019/8/14
 * 创建内容：
 */
public class TypeTwoViewHolder extends TypeViewHolder<DataModel> {
    private ImageView avatarIv;
    private TextView nameTv;
    private TextView contentTv;

    public TypeTwoViewHolder(@NonNull View itemView) {
        super(itemView);
        avatarIv = itemView.findViewById(R.id.avatar_iv);
        nameTv = itemView.findViewById(R.id.name_tv);
        contentTv = itemView.findViewById(R.id.content_tv);
    }

    @Override
    public void bindModel(DataModel model) {
        avatarIv.setBackgroundResource(model.getAvatarColor());
        nameTv.setText(model.getName());
        contentTv.setText(model.getContent());
        //contentTv.setTextColor(model.getContentColor());
    }
}
