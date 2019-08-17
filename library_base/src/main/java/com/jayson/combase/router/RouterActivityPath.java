package com.jayson.combase.router;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 * 用于组件开发中，ARouter多Activity跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */
public class RouterActivityPath {
    /**
     * 登录组件
     */
    public static class Sign {
        private static final String Sign = "/sign";
        /*登录模块*/
        public static final String PAGER_LOGIN = Sign + "/Home";
    }
}
