package com.jayson.show.bean;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * 创建人：jayson
 * 创建时间：2019/8/7
 * 创建内容：
 */
@Data
public class Node {
    private int id;
    /**
     * 根节点pid=0
     */
    private int pId = 0;
    private String name;
    /**
     * 树的层级
     */
    private int level;
    /**
     * 是否展开
     */
    private boolean isExpand;
    private int icon;
    private Node parent;
    private List<Node> children = new ArrayList<>();

    /**
     * 跳转的页面
     */
    private String className;

    /**
     * 是否是根节点
     *
     * @return
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * 判断父节点收缩状态
     *
     * @return
     */
    public boolean isParentExpand() {
        if (parent == null) {
            return false;
        }
        return parent.isExpand;
    }

    /**
     * 是否是叶子节点
     *
     * @return
     */
    public boolean isLeaf() {
        return children.size() == 0;
    }

    /**
     * 得到当前节点的层级
     *
     * @return
     */
    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    /**
     * 如果是收缩，对子节点也进行收缩
     *
     * @param isExpand
     */
    public void setExpand(boolean isExpand) {
        this.isExpand = isExpand;
        if (!isExpand) {
            for (Node node : children) {
                node.setExpand(false);
            }
        }
    }

    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public Node(int id, int pId, String name, String className) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.className = className;
    }

    public Node() {
    }
}
