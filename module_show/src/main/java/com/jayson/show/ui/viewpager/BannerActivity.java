package com.jayson.show.ui.viewpager;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.jayson.show.R;
import com.jayson.show.databinding.ActivityBannerBinding;
import com.jayson.show.ui.viewpager.view.transformer.RotateTransformer;
import com.jayson.show.ui.viewpager.view.transformer.ScaleTransformer;

/**
 * ViewPager扩展-bannner效果
 */
public class BannerActivity extends AppCompatActivity {

    ActivityBannerBinding binding;

    private int[] mResIds = new int[]{
            0xffff0000, 0xff00ff00, 0xff0000ff
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_banner);
        binding.vpMain.setOffscreenPageLimit(mResIds.length);
        //简单的视图可以直接用PagerAdapter
        binding.vpMain.setAdapter(new VPAdapter());
        //设置滑动动画的核心api:setPageTransformer
        binding.vpMain.setPageTransformer(
                true, new ScaleTransformer());

        //设置第二个viewpager及动画
        binding.vpMain2.setOffscreenPageLimit(mResIds.length);
        binding.vpMain2.setPageMargin(40);
        binding.vpMain2.setAdapter(new VPAdapter());
        binding.vpMain2.setPageTransformer(
                true, new RotateTransformer(1));
        //设置第三个viewpager及动画
        binding.vpMain3.setOffscreenPageLimit(mResIds.length);
        binding.vpMain3.setPageMargin(40);
        binding.vpMain3.setAdapter(new VPAdapter());
        binding.vpMain3.setPageTransformer(
                true, new RotateTransformer(2));

    }

    class VPAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mResIds.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View view = new View(container.getContext());
            view.setBackgroundColor(mResIds[position]);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
