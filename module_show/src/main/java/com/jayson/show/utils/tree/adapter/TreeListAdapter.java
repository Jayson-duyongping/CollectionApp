package com.jayson.show.utils.tree.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.jayson.show.bean.Node;
import com.jayson.show.utils.tree.TreeHelper;

import java.util.List;

/**
 * 创建人：jayson
 * 创建时间：2019/8/8
 * 创建内容：树形菜单adapter的一个抽象
 */
public abstract class TreeListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<Node> mAllNodes;
    protected List<Node> mVisibleNodes;

    protected LayoutInflater mInflater;

    protected ListView mTree;

    /**
     * 设置Node的点击回调
     */
    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

    private OnTreeNodeClickListener mNodeListener;

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener mNodeListener) {
        this.mNodeListener = mNodeListener;
    }

    public TreeListAdapter(Context context, ListView tree, List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultExpandLevel);
        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);

        mTree = tree;
        //点击收缩或者展开
        mTree.setOnItemClickListener((parent, view, position, id) -> {
            expandOrCollapse(position);
            //node点击
            if (mNodeListener != null) {
                mNodeListener.onClick(mVisibleNodes.get(position), position);
            }
        });
    }

    /**
     * 点击收缩或者展开
     *
     * @param position
     */
    private void expandOrCollapse(int position) {
        Node n = mVisibleNodes.get(position);
        if (n != null) {
            if (n.isLeaf()) {
                return;
            }
            n.setExpand(!n.isExpand());
            //点击之后可见节点肯定改变了，要重新过滤一次
            mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mVisibleNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mVisibleNodes.get(position);
        convertView = getConvertView(node, position, convertView, parent);
        convertView.setPadding(
                node.getLevel() * 30, 3, 3, 3);
        return convertView;
    }

    //让用户自己去创建列表item的样式
    public abstract View getConvertView(
            Node node, int position, View convertView, ViewGroup parent);
}
