package com.jayson.me.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jayson.combase.router.RouterFragmentPath;
import com.jayson.me.BR;
import com.jayson.me.R;
import com.jayson.me.databinding.MeFragmentMeBinding;
import com.jayson.me.ui.view_model.MeViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 */
@Route(path = RouterFragmentPath.Me.PAGER_ME)
public class MeFragment extends BaseFragment<MeFragmentMeBinding, MeViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.me_fragment_me;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
}

    @Override
    public void initData() {
        super.initData();
    }
}
