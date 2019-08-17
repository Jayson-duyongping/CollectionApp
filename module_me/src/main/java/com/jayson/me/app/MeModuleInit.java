package com.jayson.me.app;

import android.app.Application;

import com.jayson.combase.base.IModuleInit;

import me.goldze.mvvmhabit.utils.KLog;

/**
 * 创建人：jayson
 * 创建时间：2019/8/3
 * 创建内容：
 */
public class MeModuleInit implements IModuleInit {
    @Override
    public boolean onInitAhead(Application application) {
        KLog.e("Me模块初始化 -- onInitAhead");
        return false;
    }

    @Override
    public boolean onInitLow(Application application) {
        KLog.e("Me模块初始化 -- onInitLow");
        return false;
    }
}
