package com.jayson.show.ui.viewpager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivityVpTabBinding;
import com.jayson.show.ui.viewpager.fragment.TabFragment;
import com.jayson.show.ui.viewpager.view.TabView;
import com.jayson.show.utils.L;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.goldze.mvvmhabit.utils.ToastUtils;

/**
 * ViewPager_Tab仿微信主界面
 */
public class VpTabActivity extends AppCompatActivity {

    private static final String BUNDLE_KEY_POS = "bundle_key_pos";
    private int mCuTabPos;

    ActivityVpTabBinding binding;

    private List<String> mTitles = new ArrayList<>(
            Arrays.asList("微信", "通讯录", "发现", "我"));

    private List<TabView> mTabs = new ArrayList<>();

    //类似map,但是效率比map高很多
    private SparseArray<TabFragment> fragments = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_vp_tab);
        //翻转屏幕，获取上一次记录的当前页下标
        if (savedInstanceState != null) {
            mCuTabPos = savedInstanceState.getInt(BUNDLE_KEY_POS, 0);
        }
        //viewPager
        initViewPagerAdapter();
        //事件
        onInitEvent();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_KEY_POS, binding.vpMain.getCurrentItem());
        super.onSaveInstanceState(outState);
    }

    /**
     * viewPagerAdapter初始化
     */
    private void initViewPagerAdapter() {
        binding.vpMain.setOffscreenPageLimit(mTitles.size());
        //FragmentPagerAdapter和FragmentStatePagerAdapter
        //前者在回收fragment的时候并不会销毁，后者会销毁
        //所以针对几个tab，一般用前者，针对大量fragment，如图库，用后者
        binding.vpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                //getItem相当于create一个fragment
                TabFragment tabFragment = TabFragment.newInstance(mTitles.get(i));
                return tabFragment;
            }

            @Override
            public int getCount() {
                return mTitles.size();
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                TabFragment fragment = (TabFragment) super.instantiateItem(container, position);
                //做到界面有有几个fragment,集合中就有几个fragment，而且一一对应
                //注意：不要在Activity初始化的时候一次性把fragment加到集合，
                //这样在activity重建恢复的时候，会创建新的fragment
                fragments.put(position, fragment);
                //监听fragment中的接口(反转屏幕监听可能也会消失，所以在初始化的时候重新绑定)
                fragment.setTitleListener(title ->
                        ToastUtils.showShort("点击标题：" + title));
                return fragment;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //移除
                fragments.remove(position);
                super.destroyItem(container, position, object);
            }
        });
        binding.vpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                //positionOffset
                //左->右 left progress:1-0,right progress:0-1
                //右->左 left progress:0-1,right progress:1-0
                //根据positionOffset来进行进度样式显示
                if (positionOffset > 0) {
                    TabView left = mTabs.get(position);
                    TabView right = mTabs.get(position + 1);
                    left.setProgress(1 - positionOffset);
                    right.setProgress(positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                L.d("onPageSelected position=%d", position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 事件处理
     */
    private void onInitEvent() {
        binding.wxTab.setIconAndText(R.mipmap.tab_word_nommal, R.mipmap.tab_word, "微信");
        binding.friendTab.setIconAndText(R.mipmap.tab_sentence_nommal, R.mipmap.tab_sentence, "通讯录");
        binding.findTab.setIconAndText(R.mipmap.tab_query_nommal, R.mipmap.tab_query, "发现");
        binding.meTab.setIconAndText(R.mipmap.tab_mine_nommal, R.mipmap.tab_mine, "我");
        mTabs.add(binding.wxTab);
        mTabs.add(binding.friendTab);
        mTabs.add(binding.findTab);
        mTabs.add(binding.meTab);
        //默认选中第一页
        setCurrentTab(mCuTabPos);
        //点击事件
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            int finalI = i;
            tabView.setOnClickListener(v -> {
                //选中viewPager页
                binding.vpMain.setCurrentItem(finalI, false);
                setCurrentTab(finalI);
            });
        }
    }

    /**
     * 设置选中那个tab
     *
     * @param pos
     */
    private void setCurrentTab(int pos) {
        for (int i = 0; i < mTabs.size(); i++) {
            TabView tabView = mTabs.get(i);
            if (i == pos) {
                tabView.setProgress(1);
            } else {
                tabView.setProgress(0);
            }
        }
    }
}
