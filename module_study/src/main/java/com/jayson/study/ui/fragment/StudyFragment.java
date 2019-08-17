package com.jayson.study.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jayson.combase.router.RouterFragmentPath;
import com.jayson.study.BR;
import com.jayson.study.R;
import com.jayson.study.databinding.StudyFragmentStudyBinding;
import com.jayson.study.ui.view_model.StudyViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 */
@Route(path = RouterFragmentPath.Study.PAGER_Study)
public class StudyFragment extends BaseFragment<StudyFragmentStudyBinding, StudyViewModel> {
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.study_fragment_study;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
}

    @Override
    public void initData() {
    }
}
