package com.jayson.show.ui.viewpager.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jayson.show.R;
import com.jayson.show.databinding.FragmentSplashBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SplashFragment extends Fragment {

    public static final String BUNDLE_KEY_RES_ID = "key_res_id";

    FragmentSplashBinding binding;

    public static SplashFragment newInstance(int resId) {
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_KEY_RES_ID, resId);
        SplashFragment splashFragment = new SplashFragment();
        splashFragment.setArguments(bundle);
        return splashFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //可以做一些界面初始化
        Bundle bundle = getArguments();
        if (bundle != null) {
            int resId = bundle.getInt(BUNDLE_KEY_RES_ID);
            binding.contentIv.setImageResource(resId);
            binding.contentIv.setTag(resId);
        }
    }
}
