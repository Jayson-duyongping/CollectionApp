package com.jayson.show.bean;

import com.jayson.show.utils.tree.annotation.NodeClass;
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
public class FileBean {
    @NodeId
    private int id;
    @NodePid
    private int pId;
    @NodeName
    private String name;
    private String desc;

    @NodeClass
    private String className;

    public FileBean(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public FileBean(int id, int pId, String name, String className) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.className = className;
    }
}
