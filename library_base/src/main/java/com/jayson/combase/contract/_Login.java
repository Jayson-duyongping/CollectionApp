package com.jayson.combase.contract;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：登录完成后，组件间通信的契约类
 */
public class _Login {
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
