package com.jayson.main.app;

import android.app.Application;

import com.jayson.combase.base.IModuleInit;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * 创建人：jayson
 * 创建时间：2019/8/2
 * 创建内容：组件模块实现IModuleInit，相当于做Application初始化
 */
public class MainModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("主业务模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("主业务模块初始化 -- onInitLow");
        return false;
    }
}
