package com.jayson.show.ui.recyclerview;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivityWaterfallBinding;
import com.jayson.show.ui.recyclerview.adapter.WaterfallAdapter;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * 瀑布流RecyclerView
 */
public class WaterfallActivity extends AppCompatActivity {

    ActivityWaterfallBinding binding;

    private List<String> mDatas;

    private WaterfallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_waterfall);
        //设置布局管理器StaggeredGridLayoutManager
        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                3, StaggeredGridLayoutManager.VERTICAL));

        initData();
        adapter = new WaterfallAdapter(this, mDatas);
        binding.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new WaterfallAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int item) {
                ToastUtils.showShort("点击：" + item);
            }

            @Override
            public void onItemLongClick(View view, int item) {
                ToastUtils.showShort("长按：" + item);
            }
        });
    }

    /**
     * 模拟初始数据
     */
    private void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add(((char) i) + "");
        }
    }

    public void onAdd(View view) {
        adapter.addData(1);
    }

    public void onMove(View view) {
        adapter.removeData(1);
    }
}
