package com.jayson.home.ui.view_model;

import android.app.Application;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.jayson.home.BR;
import com.jayson.home.R;
import com.jayson.home.ui.adapter.ViewPagerBindingAdapter;

import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.binding.command.BindingConsumer;
import me.goldze.mvvmhabit.bus.event.SingleLiveEvent;
import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：Home视图模型
 */
public class HomeViewModel extends BaseViewModel {

    public SingleLiveEvent<String> itemClickEvent = new SingleLiveEvent<>();

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void addPage() {
        items.clear();
        //模拟3个ViewPager页面
        for (int i = 1; i <= 3; i++) {
            ViewPagerItemViewModel itemViewModel = new ViewPagerItemViewModel(this, "第" + i + "个页面");
            items.add(itemViewModel);
        }
    }

    //给ViewPager添加items
    public ObservableList<ViewPagerItemViewModel> items = new ObservableArrayList<>();
    //给ViewPager添加ItemBinding
    public ItemBinding<ViewPagerItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.home_item_viewpager);
    //给ViewPager添加PageTitle
    public final BindingViewPagerAdapter.PageTitles<ViewPagerItemViewModel> pageTitles
            = new BindingViewPagerAdapter.PageTitles<ViewPagerItemViewModel>() {
        @Override
        public CharSequence getPageTitle(int position, ViewPagerItemViewModel item) {
            return "条目" + position;
        }
    };
    //给ViewPager添加Adpter，请使用自定义的Adapter继承BindingViewPagerAdapter，重写onBindBinding方法
    public final ViewPagerBindingAdapter adapter = new ViewPagerBindingAdapter();
    //ViewPager切换监听
    public BindingCommand<Integer> onPageSelectedCommand = new BindingCommand<>(new BindingConsumer<Integer>() {
        @Override
        public void call(Integer index) {
            //ToastUtils.showShort("ViewPager切换：" + index);
        }
    });
}
