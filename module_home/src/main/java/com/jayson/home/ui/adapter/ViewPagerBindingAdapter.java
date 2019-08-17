package com.jayson.home.ui.adapter;

import android.databinding.ViewDataBinding;
import android.view.ViewGroup;

import com.jayson.home.databinding.HomeItemViewpagerBinding;
import com.jayson.home.ui.view_model.ViewPagerItemViewModel;

import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 */
public class ViewPagerBindingAdapter extends BindingViewPagerAdapter<ViewPagerItemViewModel> {

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, int layoutRes, int position, ViewPagerItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        //这里可以强转成ViewPagerItemViewModel对应的ViewDataBinding，
        HomeItemViewpagerBinding _binding = (HomeItemViewpagerBinding) binding;
    }
    
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

}
