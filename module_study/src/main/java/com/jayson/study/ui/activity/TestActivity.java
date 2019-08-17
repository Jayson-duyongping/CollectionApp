package com.jayson.study.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jayson.study.R;
import com.jayson.study.databinding.StudyActivityTestBinding;

import java.util.ArrayList;
import java.util.List;

import me.goldze.mvvmhabit.utils.ToastUtils;

public class TestActivity extends AppCompatActivity {

    StudyActivityTestBinding binding;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil
                .setContentView(this, R.layout.study_activity_test);
        binding.setPresenter(new Presenter());
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(this);
        binding.recyclerView.setAdapter(userAdapter);
        userAdapter.setListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onUserClick(User user) {
                ToastUtils.showShort("点击了" + user.getName());
            }
        });
        List<User> users = new ArrayList<>();
        users.add(new User("唐三", 25, true));
        users.add(new User("小舞", 23, false));
        users.add(new User("奥斯卡", 26, true));
        userAdapter.addAll(users);
    }

    public class Presenter {
        public void onClickAddItem(View view) {
            userAdapter.add(new User("观众", 0, true));
        }

        public void onClickRemoveItem(View view) {
            userAdapter.remove();
        }
    }

    /**
     * UserAdapter
     */
    public static class UserAdapter extends RecyclerView.Adapter<BindingViewHolder> {

        private static final int ITEM_VIEW_TYPE_MAN = 1;
        private static final int ITEM_VIEW_TYPE_WOMAN = 2;

        private final LayoutInflater mLayoutInflater;
        private OnItemClickListener mListener;
        private List<User> users;

        public void setListener(OnItemClickListener mListener) {
            this.mListener = mListener;
        }

        public UserAdapter(Context context) {
            mLayoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            users = new ArrayList<>();
        }

        public interface OnItemClickListener {
            void onUserClick(User user);
        }

        public void addAll(List<User> data) {
            users.addAll(data);
        }

        public void add(User user) {
            users.add(user);
            notifyItemInserted(users.size());
        }

        public void remove() {
            if (users.size() == 0) {
                return;
            }
            users.remove(0);
            notifyItemRemoved(0);
        }

        @Override
        public int getItemViewType(int position) {
            final User user = users.get(position);
            if (user.isMan) {
                return ITEM_VIEW_TYPE_MAN;
            } else {
                return ITEM_VIEW_TYPE_WOMAN;
            }
        }

        @NonNull
        @Override
        public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            ViewDataBinding binding;
            if (viewType == ITEM_VIEW_TYPE_MAN) {
                binding = DataBindingUtil.inflate(mLayoutInflater,
                        R.layout.study_item_test1, viewGroup, false);
            } else {
                binding = DataBindingUtil.inflate(mLayoutInflater,
                        R.layout.study_item_test2, viewGroup, false);
            }
            return new BindingViewHolder<>(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {
            final User user = users.get(position);
            holder.getBinding().setVariable(com.jayson.study.BR.item, user);
            holder.getBinding().executePendingBindings();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onUserClick(user);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return users.size();
        }
    }

    /**
     * BindingViewHolder
     *
     * @param <T>
     */
    public static class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

        private T mBinding;

        public BindingViewHolder(@NonNull T binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        public T getBinding() {
            return mBinding;
        }
    }

    public class User {
        private String name;
        private int age;
        private boolean isMan;

        public User(String name, int age, boolean isMan) {
            this.name = name;
            this.age = age;
            this.isMan = isMan;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isMan() {
            return isMan;
        }

        public void setMan(boolean man) {
            isMan = man;
        }
    }
}
