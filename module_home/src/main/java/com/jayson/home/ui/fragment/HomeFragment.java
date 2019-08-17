package com.jayson.home.ui.fragment;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jayson.combase.router.RouterFragmentPath;
import com.jayson.home.BR;
import com.jayson.home.R;
import com.jayson.home.databinding.HomeFragmentHomeBinding;
import com.jayson.home.ui.view_model.HomeViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 */
@Route(path = RouterFragmentPath.Home.PAGER_HOME)
public class HomeFragment extends BaseFragment<HomeFragmentHomeBinding, HomeViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.home_fragment_home;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
}

    @Override
    public void initData() {
        // 使用 TabLayout 和 ViewPager 相关联
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
        viewModel.addPage();
    }

    @Override
    public void initViewObservable() {
        viewModel.itemClickEvent.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ToastUtils.showShort(s);
            }
        });
    }
}
