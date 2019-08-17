package com.jayson.show.ui.recyclerview;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivityRecyclerBinding;
import com.jayson.show.ui.recyclerview.adapter.SimpleAdapter;
import com.jayson.show.ui.recyclerview.bean.DataModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends AppCompatActivity {

    ActivityRecyclerBinding binding;

    private SimpleAdapter demoAdapter;

    private Context context;

    int color[] = {android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark,
            android.R.color.holo_orange_dark};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil
                .setContentView(this, R.layout.activity_recycler);
        context = this;
        //设置RecyclerView布局管理器
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false));
        //设置RecyclerView分割线
        DividerItemDecoration divider = new DividerItemDecoration(
                context, DividerItemDecoration.VERTICAL);
        //一个自定义的分割线
        Drawable mDrawable = ContextCompat.getDrawable(context, R.drawable.divider_item);
        divider.setDrawable(mDrawable);
        binding.recyclerView.addItemDecoration(divider);

        demoAdapter = new SimpleAdapter(context);
        binding.recyclerView.setAdapter(demoAdapter);
        //初始化数据
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        List<DataModel> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int type = (int) ((Math.random() * 3) + 1);
            DataModel model = new DataModel();
            model.setAvatarColor(color[type - 1]);
            model.setType(type);
            model.setName("name" + i);
            model.setContent("content" + i);
            model.setContentColor(color[(type + 1) % 3]);
            list.add(model);
        }
        demoAdapter.addList(list);
        demoAdapter.notifyDataSetChanged();
    }

    /**
     * 横向Grid
     * 需要设置布局中item的宽度
     *
     * @param view
     */
    public void changeStaggeredGrid(View view) {
        binding.recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.HORIZONTAL));
    }

    /**
     * Grid
     *
     * @param view
     */
    public void changeGrid(View view) {
        binding.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
    }

    /**
     * List
     *
     * @param view
     */
    public void changeList(View view) {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
