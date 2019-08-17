package com.jayson.show.bean;

import com.jayson.show.utils.tree.annotation.NodeId;
import com.jayson.show.utils.tree.annotation.NodeName;
import com.jayson.show.utils.tree.annotation.NodePid;

import lombok.Data;

/**
 * 创建人：jayson
 * 创建时间：2019/8/7
 * 创建内容：
 */
@Data
public class OrgBean {
    @NodeId
    private int _id;
    @NodePid
    private int parentId;
    @NodeName
    private String name;
}
