package com.jayson.combase.config;

/**
 * 创建人：jayson
 * 创建时间：2019/8/2
 * 创建内容：
 * 组件生命周期反射类名管理，在这里注册需要初始化的组件，
 * 通过反射动态调用各个组件的初始化方法
 * 注意：以下模块中初始化的Module类不能被混淆
 */
public class ModuleLifecycleReflexs {
    private static final String BaseInit = "com.jayson.combase.base.BaseModuleInit";
    //主业务模块
    private static final String MainInit = "com.jayson.main.app.MainModuleInit";
    //首页业务模块
    private static final String HomeInit = "com.jayson.home.app.HomeModuleInit";
    //学习业务模块
    private static final String StudyInit = "com.jayson.study.app.StudyModuleInit";
    //娱乐业务模块
    private static final String FunInit = "com.jayson.fun.app.FunModuleInit";
    //我的业务模块
    private static final String MeInit = "com.jayson.me.app.MeModuleInit";
    //登录业务模块
    private static final String SignInit = "com.jayson.sign.app.SignModuleInit";

    //显示测试模块
    private static final String ShowInit = "com.jayson.show.app.ShowModuleInit";
    //Demo测试模块
    private static final String DemoInit = "com.jayson.demo.app.DemoModuleInit";

    public static String[] initModuleNames = {BaseInit, MainInit, HomeInit, StudyInit, FunInit, MeInit, SignInit, ShowInit, DemoInit};
}
