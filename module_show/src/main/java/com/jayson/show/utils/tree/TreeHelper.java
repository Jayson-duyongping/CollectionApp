package com.jayson.show.utils.tree;

import com.jayson.show.R;
import com.jayson.show.bean.Node;
import com.jayson.show.utils.tree.annotation.NodeClass;
import com.jayson.show.utils.tree.annotation.NodeId;
import com.jayson.show.utils.tree.annotation.NodeName;
import com.jayson.show.utils.tree.annotation.NodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：jayson
 * 创建时间：2019/8/7
 * 创建内容：
 */
public class TreeHelper {
    /**
     * 用户数据Bean转换为树形Node
     *
     * @param <T>
     * @return
     */
    public static <T> List<Node> convertDatas2Nodes(List<T> datas) throws IllegalAccessException {
        List<Node> nodes = new ArrayList<>();
        //-*-*-*-将用户数集转换为node集-*-*-*-
        for (T t : datas) {
            int id = -1;
            int pid = -1;
            String name = null;
            String className = null;
            Node node;
            //注解+反射遍历获取字段中的值
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                if (f.getAnnotation(NodeId.class) != null) {
                    f.setAccessible(true);
                    id = f.getInt(t);
                }
                if (f.getAnnotation(NodePid.class) != null) {
                    f.setAccessible(true);
                    pid = f.getInt(t);
                }
                if (f.getAnnotation(NodeName.class) != null) {
                    f.setAccessible(true);
                    name = (String) f.get(t);
                }
                if (f.getAnnotation(NodeClass.class) != null) {
                    f.setAccessible(true);
                    className = (String) f.get(t);
                }
            }
            node = new Node(id, pid, name, className);
            nodes.add(node);
        }

        //-*-*-*-设置node的关联关系-*-*-*-
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getPId() == n.getId()) {
                    //m是n的子集
                    m.setParent(n);
                    n.getChildren().add(m);
                } else if (m.getId() == n.getPId()) {
                    //m是n的父级
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }
        //-*-*-*-设置node图标-*-*-*-
        for (Node n : nodes) {
            setNodeIcon(n);
        }
        return nodes;
    }

    /**
     * -*-*-*-排序-*-*-*-
     *
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel) throws IllegalAccessException {
        List<Node> result = new ArrayList<>();
        //所有节点
        List<Node> nodes = convertDatas2Nodes(datas);
        //获取树的根节点
        List<Node> rootNodes = getRootNodes(nodes);
        //循环放置子节点到父节点
        for (Node node : rootNodes) {
            addNode(result, node, defaultExpandLevel, 1);
        }
        return result;
    }

    /**
     * 把一个节点的所有孩子节点都放入result
     *
     * @param result             所有节点
     * @param node               父节点
     * @param defaultExpandLevel 层级
     * @param currentLevel       当前level
     */
    private static void addNode(List<Node> result, Node node, int defaultExpandLevel, int currentLevel) {
        result.add(node);
        if (defaultExpandLevel >= currentLevel) {
            node.setExpand(true);
        }
        //是否是叶子节点
        if (node.isLeaf()) {
            return;
        }
        //依次递归
        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(result, node.getChildren().get(i),
                    defaultExpandLevel, currentLevel + 1);
        }
    }

    /**
     * 过滤出可见节点
     *
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> nodes) {
        List<Node> result = new ArrayList<>();
        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpand()) {
                setNodeIcon(node);
                result.add(node);
            }
        }
        return result;
    }

    /**
     * 从所有节点中过滤出根节点
     *
     * @param nodes
     * @return
     */
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<>();
        for (Node n : nodes) {
            if (n.isRoot()) {
                root.add(n);
            }
        }
        return root;
    }

    /**
     * 为Node设置图标
     *
     * @param n
     */
    private static void setNodeIcon(Node n) {
        if (n.getChildren().size() > 0 && n.isExpand()) {
            //展开
            n.setIcon(R.mipmap.span2);
        } else if (n.getChildren().size() > 0 && !n.isExpand()) {
            //收缩
            n.setIcon(R.mipmap.span1);
        } else {
            //没有子集
            n.setIcon(-1);
        }
    }
}
