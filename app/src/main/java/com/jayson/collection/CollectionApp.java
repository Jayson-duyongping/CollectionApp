package com.jayson.collection;

import android.app.Application;

import com.jayson.combase.config.ModuleLifecycleConfig;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：宿主Application，执行初始化和组件初始化
 */
public class CollectionApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化组件(靠前)
        ModuleLifecycleConfig.getInstance().initModuleAhead(this);
        //初始化组件(靠后)
        ModuleLifecycleConfig.getInstance().initModuleLow(this);
    }
}
