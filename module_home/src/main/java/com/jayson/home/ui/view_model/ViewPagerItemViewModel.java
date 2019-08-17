package com.jayson.home.ui.view_model;

import android.support.annotation.NonNull;

import me.goldze.mvvmhabit.base.ItemViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：ViewPager-Item视图模型
 */
public class ViewPagerItemViewModel extends ItemViewModel<HomeViewModel> {

    public String text;

    public ViewPagerItemViewModel(@NonNull HomeViewModel viewModel, String text) {
        super(viewModel);
        this.text = text;
    }

    //点击事件
    public BindingCommand onClick = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            //点击之后将逻辑转到adapter中处理
            viewModel.itemClickEvent.setValue(text);
        }
    });
}
