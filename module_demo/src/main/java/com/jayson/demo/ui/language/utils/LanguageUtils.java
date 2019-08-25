package com.jayson.demo.ui.language.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.jayson.demo.ui.language.bean.LanguageRegion;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 创建人：jayson
 * 创建时间：2019/8/24
 * 创建内容：
 */
public class LanguageUtils {
    /**
     * 获取当前的选中的语言
     *
     * @param context 建议用ApplicationContext
     * @return
     */
    public static LanguageRegion getLanguage(Context context) {
        //取缓存
        LanguageRegion region = (LanguageRegion) SpHelper.getInstance(context)
                .getBeanByFastJson("currentLanguage", LanguageRegion.class);
        if (region != null) {
            return region;
        }
        //获取系统的
        Locale locale = getResourceLocale(context);
        return new LanguageRegion(
                locale.getDisplayCountry(),
                locale.getDisplayLanguage(),
                locale.getLanguage() + "-" + locale.getCountry());
    }

    /**
     * 设置语言
     *
     * @param context
     * @param locale
     */
    public static void setLanguage(Context context, Locale locale) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        context.getResources().updateConfiguration(configuration, metrics);
    }

    /**
     * 获取语言列表
     *
     * @return
     */
    public static List<LanguageRegion> getLanguageList() {
        List<LanguageRegion> lanList = new ArrayList<>();
        lanList.add(new LanguageRegion("中国", "简体中文", Locale.SIMPLIFIED_CHINESE));
        lanList.add(new LanguageRegion("台湾", "繁体中文", Locale.TAIWAN));
        lanList.add(new LanguageRegion("美国", "English", Locale.US));
        lanList.add(new LanguageRegion("日本", "日本語", Locale.JAPAN));
        return lanList;
    }

    /**
     * 获取所有语言
     *
     * @return
     */
    public static List<LanguageRegion> getSysLanguageList() {
        List<LanguageRegion> lanList = new ArrayList<>();
        Locale[] locales = getAndroidLocaleList();
        for (Locale locale : locales) {
            //去掉一些没有意义的Locale
            String disName = locale.getDisplayName(Locale.forLanguageTag(locale.getLanguage()));
            if (TextUtils.isEmpty(disName) || !disName.contains("(")) {
                continue;
            }
            //构建实体
            LanguageRegion region = new LanguageRegion(
                    locale.getDisplayCountry(),
                    locale.getDisplayLanguage(),
                    locale.getLanguage() + "-" + locale.getCountry());
            region.setShowName(disName);
            if (disName.contains("(")
                    && disName.contains(",")) {
                String la = disName.substring(disName.lastIndexOf("(") + 1, disName.length() - 1);
                region.setShowName(la);
            }
            Log.d("LAN", region.getShowName());
            lanList.add(region);

        }
        return lanList;
    }

    /**
     * 获取当前Locale
     *
     * @param context 建议用ApplicationContext
     * @return
     */
    public static Locale getResourceLocale(Context context) {
        Configuration conf = context.getResources().getConfiguration();
        //SDK大于7.0版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return conf.getLocales().get(0);
        }
        //7.0以前
        return conf.locale;
    }

    /**
     * 获取android所有语言
     *
     * @return
     */
    public static Locale[] getAndroidLocaleList() {
        Locale[] lanList = Locale.getAvailableLocales();
        return lanList;
    }
}
