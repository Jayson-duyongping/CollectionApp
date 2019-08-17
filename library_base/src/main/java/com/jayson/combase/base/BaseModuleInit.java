package com.jayson.combase.base;

import android.app.Application;

import com.alibaba.android.arouter.BuildConfig;
import com.alibaba.android.arouter.launcher.ARouter;

import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.utils.KLog;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：基础库自身初始化操作
 */
public class BaseModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.init(true);
        //初始化阿里路由框架
        if (BuildConfig.DEBUG) {
            ARouter.openLog();//打印日志
            ARouter.openDebug();//开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        //尽可能早，推荐在Application中初始化
        ARouter.init(application);
        //MVVMHabit
        BaseApplication.setApplication(application);
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        return false;
    }
}
