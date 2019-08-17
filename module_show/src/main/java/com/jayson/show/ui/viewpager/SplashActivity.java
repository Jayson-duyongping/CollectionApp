package com.jayson.show.ui.viewpager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivitySplashBinding;
import com.jayson.show.ui.viewpager.fragment.SplashFragment;
import com.jayson.show.ui.viewpager.view.transformer.ScaleTransformer;

/**
 * ViewPager扩展-图片显示
 */
public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    private int[] mResIds = new int[]{
            R.mipmap.page1,
            R.mipmap.page2,
            R.mipmap.page3,
            R.mipmap.page4,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_splash);
        //FragmentStatePagerAdapter更好地管理内存
        binding.vpMain.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return SplashFragment.newInstance(mResIds[i]);
            }

            @Override
            public int getCount() {
                return mResIds.length;
            }
        });
        //设置滑动动画的核心api:setPageTransformer
        binding.vpMain.setPageTransformer(
                true, new ScaleTransformer());
    }
}
