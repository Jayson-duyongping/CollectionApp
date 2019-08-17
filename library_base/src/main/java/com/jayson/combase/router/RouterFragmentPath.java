package com.jayson.combase.router;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 * 用于组件开发中，ARouter多Fragment跳转的统一路径注册
 * 在这里注册添加路由路径，需要清楚的写好注释，标明功能界面
 */
public class RouterFragmentPath {
    /**
     * 首页组件
     */
    public static class Home {
        private static final String HOME = "/home";
        /*首页*/
        public static final String PAGER_HOME = HOME + "/Home";
    }

    /**
     * 学习组件
     */
    public static class Study {
        private static final String Study = "/study";
        /*学习*/
        public static final String PAGER_Study = Study + "/Study";
    }

    /**
     * 娱乐组件
     */
    public static class Fun {
        private static final String Fun = "/fun";
        /*娱乐*/
        public static final String PAGER_Fun= Fun + "/Fun";
    }

    /**
     * 用户组件
     */
    public static class Me {
        private static final String Me = "/me";
        /*我的*/
        public static final String PAGER_ME = Me + "/Me";
    }
}
