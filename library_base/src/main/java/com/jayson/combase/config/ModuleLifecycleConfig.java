package com.jayson.combase.config;

import android.app.Application;

import com.jayson.combase.base.IModuleInit;

import io.reactivex.annotations.Nullable;

/**
 * 创建人：jayson
 * 创建时间：2019/8/2
 * 创建内容：作为组件生命周期初始化的配置类，通过反射机制，动态调用每个组件初始化逻辑
 * <p>
 * 组件多了，必定会涉及到初始化的先后顺序问题，组件中依赖的第三方库，
 * 有些库需要尽早初始化，有些可以稍晚一些，
 * 所以我们定义两个方法initModuleAhead和initModuleLow
 */
public class ModuleLifecycleConfig {

    //内部类，在装载该内部类时才会去创建单例对象
    private static class SingletonHolder {
        public static ModuleLifecycleConfig instance
                = new ModuleLifecycleConfig();
    }

    public static ModuleLifecycleConfig getInstance() {
        return SingletonHolder.instance;
    }

    private ModuleLifecycleConfig() {
    }

    /**
     * 初始化组件-靠前(先初始化)
     *
     * @param application
     */
    public void initModuleAhead(@Nullable Application application) {
        for (String moduleInitName : ModuleLifecycleReflexs.initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleInitName);
                IModuleInit init = (IModuleInit) clazz.newInstance();
                //调用初始化方法
                init.onInitAhead(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 初始化组件-靠后(后初始化)
     *
     * @param application
     */
    public void initModuleLow(@Nullable Application application) {
        for (String moduleInitName : ModuleLifecycleReflexs.initModuleNames) {
            try {
                Class<?> clazz = Class.forName(moduleInitName);
                IModuleInit init = (IModuleInit) clazz.newInstance();
                //调用初始化方法
                init.onInitLow(application);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
