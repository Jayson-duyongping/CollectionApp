package com.jayson.show.ui;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jayson.show.R;
import com.jayson.show.bean.FileBean;
import com.jayson.show.bean.Node;
import com.jayson.show.data.TreeData;
import com.jayson.show.utils.L;
import com.jayson.show.utils.tree.TreeHelper;
import com.jayson.show.utils.tree.adapter.TreeListAdapter;

import java.util.List;

/**
 * 这个树形ListView是很早之前鸿洋大神写的，
 * 有一些思想很不错，主要在TreeHelper和TreeListAdapter
 * 1树形结构的数据的组织
 * 2数据结果的进一步加强
 * 3反射+注解的使用(注解可以被命名规范所代替 id nodeId)
 * 4封装与设计我们的Adapter
 */
public class ShowActivity extends AppCompatActivity {

    private Context context;
    private ListView mTree;
    private SimpleTreeListAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        context = this;
        mTree = findViewById(R.id.tree_lv);
        initData();
        testMemory();
    }

    private void initData() {
        //获取数节点数据
        mDatas = TreeData.getTreeData();
        try {
            mAdapter = new SimpleTreeListAdapter<>(context, mTree, mDatas, 0);
            mTree.setAdapter(mAdapter);
            initEvent();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        //一般叶子节点的点击事件
        mAdapter.setOnTreeNodeClickListener((node, position) -> {
            if (node.isLeaf()) {
                setIntentGo(node.getClassName());
            }
        });
        //长按事件
        mTree.setOnItemLongClickListener((parent, view, position, id) -> {
            //官方推荐DialogFragment
            EditText et = new EditText(context);
            new AlertDialog.Builder(context).setTitle("Add Node")
                    .setView(et)
                    .setPositiveButton("Sure", (dialog, which) -> {
                        mAdapter.addExtraNode(position,
                                et.getText().toString());
                    }).setNegativeButton("Cancel", null)
                    .show();
            //LongClick出发之后click就不会触发了
            return true;
        });
    }

    /**
     * 对应跳转
     *
     * @param className
     */
    private void setIntentGo(String className) {
        if (TextUtils.isEmpty(className)) {
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this, className);
        startActivity(intent);
    }

    /**
     * 继承TreeListAdapter抽象类
     *
     * @param <T>
     */
    class SimpleTreeListAdapter<T> extends TreeListAdapter<T> {

        public SimpleTreeListAdapter(Context context, ListView tree, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
            super(context, tree, datas, defaultExpandLevel);
        }

        @Override
        public View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.tree_list_item, parent, false);
                holder = new ViewHolder();
                holder.iconIv = convertView.findViewById(R.id.item_icon_iv);
                holder.nameTv = convertView.findViewById(R.id.item_name_tv);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (node.getIcon() == -1) {
                holder.iconIv.setVisibility(View.INVISIBLE);
            } else {
                holder.iconIv.setVisibility(View.VISIBLE);
                holder.iconIv.setImageResource(node.getIcon());
            }
            holder.nameTv.setText(node.getName());
            return convertView;
        }

        private class ViewHolder {
            ImageView iconIv;
            TextView nameTv;
        }

        /**
         * 动态插入节点
         *
         * @param position
         * @param name
         */
        public void addExtraNode(int position, String name) {
            Node node = mVisibleNodes.get(position);
            int indexOf = mAllNodes.indexOf(node);
            //一般是存到服务器
            Node extraNode = new Node(-1, node.getId(), name);
            extraNode.setParent(node);
            node.getChildren().add(extraNode);
            mAllNodes.add(indexOf + 1, extraNode);
            mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
        }
    }

    private void testMemory() {
        ActivityManager activityManager = (ActivityManager)
                getSystemService(Context.ACTIVITY_SERVICE);
        int memClass = activityManager.getMemoryClass();//m为单位
        int largeMemClass = activityManager.getLargeMemoryClass();//m
        L.d("memClass:%dM,largeMemClass:%dM", memClass, largeMemClass);

        //代码查看内存情况
        float totalMemory = Runtime.getRuntime().totalMemory()
                * 1.0f / (1024 * 1024);
        float freeMemory = Runtime.getRuntime().freeMemory()
                * 1.0f / (1024 * 1024);
        float maxMemory = Runtime.getRuntime().maxMemory()
                * 1.0f / (1024 * 1024);
        L.d("totalMemory:%fM,freeMemory:%fM,maxMemory:%fM",
                totalMemory, freeMemory, maxMemory);
    }
}
