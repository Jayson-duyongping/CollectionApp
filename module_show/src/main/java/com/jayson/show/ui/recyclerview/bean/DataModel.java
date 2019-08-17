package com.jayson.show.ui.recyclerview.bean;

import lombok.Data;

/**
 * 创建人：jayson
 * 创建时间：2019/8/14
 * 创建内容：
 */
@Data
public class DataModel {
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    public static final int TYPE_THREE = 3;
    private int type;
    private int avatarColor;
    private String name;
    private String content;
    private int contentColor;
}
