package com.jayson.fun.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jayson.combase.router.RouterFragmentPath;
import com.jayson.fun.BR;
import com.jayson.fun.R;
import com.jayson.fun.databinding.FunFragmentFunBinding;
import com.jayson.fun.ui.view_model.FunViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 */
@Route(path = RouterFragmentPath.Fun.PAGER_Fun)
public class FunFragment extends BaseFragment<FunFragmentFunBinding, FunViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fun_fragment_fun;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
}

    @Override
    public void initData() {
    }
}
