package com.jayson.show.utils;

import android.util.Log;

import com.jayson.show.BuildConfig;

/**
 * 创建人：jayson
 * 创建时间：2019/8/5
 * 创建内容： 日志工具类
 */
public class L {
    private static final String TAG = "SHOW_LOG";

    private static boolean sDebug = BuildConfig.DEBUG;

    public static void d(String msg, Object... args) {
        if (!sDebug) {
            return;
        }
        if (args.length == 0) {
            Log.d(TAG, msg + "");
        } else {
            Log.d(TAG, String.format(msg, args));
        }
    }
}
