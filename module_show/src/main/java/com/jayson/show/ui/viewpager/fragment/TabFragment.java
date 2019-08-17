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
import com.jayson.show.databinding.FragmentTabBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

    public static final String BUNDLE_KEY_TITLE = "key_title";

    FragmentTabBinding binding;

    /**
     * fragment向activity传递可以使用回调
     */
    public interface OnTitleClickListener {
        void onClick(String title);
    }

    private OnTitleClickListener titleListener;

    public void setTitleListener(OnTitleClickListener titleListener) {
        this.titleListener = titleListener;
    }

    /**
     * 这种方式创建fragment并传递参数，
     * 比如后台内存被杀死，再次进来
     * 当fragment尝试恢复的过程中会执行setArguments，
     * 而像fragment.title这种形式是恢复不了的。
     *
     * @return
     */
    public static TabFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_KEY_TITLE, title);
        TabFragment tabFragment = new TabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //可以做一些界面初始化
        Bundle bundle = getArguments();
        if (bundle != null) {
            String title = bundle.getString(BUNDLE_KEY_TITLE);
            binding.setTitle(title);
        }
        //点击回调
        binding.titleTv.setOnClickListener(v -> {
            if (titleListener != null) {
                titleListener.onClick(binding.titleTv.getText().toString());
            }
        });
    }

    /**
     * 向外暴露的方法
     *
     * @param title
     */
    public void changeTitle(String title) {
        //一定要判断当前fragment是否被添加，或者判断 isResumed()
        if (!isAdded()) {
            return;
        }
        binding.setTitle(title);
    }
}
