package com.jayson.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.jayson.combase.router.RouterFragmentPath;
import com.jayson.main.constant.RouteUrl;
import com.jayson.main.databinding.MainActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.base.BaseActivity;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;

/**
 * 1.
 * 首先：在Activity/Fragment类上面写上 Route path 注解。
 * 注意：这里的路径需要注意的是至少需要有两级，/xx/xx
 */
@Route(path = RouteUrl.ACTIVITY_URL_MAIN)
public class MainActivity extends BaseActivity<MainActivityMainBinding, BaseViewModel> {

    private List<Fragment> mFragments;

    //MainActivityMainBinding类是dataBinding框架自定生成的,对main_activity_main.xml
    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.main_activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public void initData() {
        //初始化Fragment
        initFragment();
        //初始化底部Button
        initBottomTab();
    }

    private void initFragment() {
        //ARouter拿到多Fragment(这里需要通过ARouter获取，不能直接new,因为在组件独立运行时，宿主app是没有依赖其他组件，所以new不到其他组件的Fragment)
        Fragment homeFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Home.PAGER_HOME).navigation();
        Fragment studyFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Study.PAGER_Study).navigation();
        Fragment funFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Fun.PAGER_Fun).navigation();
        Fragment meFragment = (Fragment) ARouter.getInstance().build(RouterFragmentPath.Me.PAGER_ME).navigation();
        mFragments = new ArrayList<>();
        mFragments.add(homeFragment);
        mFragments.add(studyFragment);
        mFragments.add(funFragment);
        mFragments.add(meFragment);
        if (homeFragment != null) {
            //默认选中第一个
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.frameLayout, homeFragment);
            transaction.commitAllowingStateLoss();
        }
    }

    private void initBottomTab() {
        NavigationController navigationController = binding.pagerBottomTab.material()
                .addItem(R.mipmap.tab_word, "首页")
                .addItem(R.mipmap.tab_sentence, "学习")
                .addItem(R.mipmap.tab_query, "娱乐")
                .addItem(R.mipmap.tab_mine, "我的")
                .setDefaultColor(ContextCompat.getColor(this, R.color.textColorVice))
                .build();
        //底部按钮的点击事件监听
        navigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                Fragment currentFragment = mFragments.get(index);
                Log.d("AAAAAAAAAAA",index + ":" + currentFragment);
                if (currentFragment != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frameLayout, currentFragment);
                    transaction.commitAllowingStateLoss();
                }
            }

            @Override
            public void onRepeat(int index) {
            }
        });
    }
}
