package com.jayson.show.data;

import com.jayson.show.bean.FileBean;
import com.jayson.show.ui.customview.DoubleCacheActivity;
import com.jayson.show.ui.customview.DrawerActivity;
import com.jayson.show.ui.customview.FlowLayoutActivity;
import com.jayson.show.ui.customview.Graphics2DActivity;
import com.jayson.show.ui.customview.SgbActivity;
import com.jayson.show.ui.customview.ViewActivity;
import com.jayson.show.ui.customview.ViewGroupActivity;
import com.jayson.show.ui.recyclerview.RecyclerActivity;
import com.jayson.show.ui.recyclerview.WaterfallActivity;
import com.jayson.show.ui.viewpager.BannerActivity;
import com.jayson.show.ui.viewpager.SplashActivity;
import com.jayson.show.ui.viewpager.VpTabActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建人：jayson
 * 创建时间：2019/8/14
 * 创建内容：
 */
public class TreeData {
    /**
     * 控件数的数据
     *
     * @return
     */
    public static List<FileBean> getTreeData() {
        List<FileBean> mDatas = new ArrayList<>();
        FileBean vpBean = new FileBean(1, 0, "ViewPager");
        FileBean bean1 = new FileBean(1001, 1, "Vp+tab", VpTabActivity.class.getName());
        FileBean bean2 = new FileBean(1002, 1, "Splash", SplashActivity.class.getName());
        FileBean bean3 = new FileBean(1003, 1, "Banner", BannerActivity.class.getName());
        mDatas.add(vpBean);
        mDatas.add(bean1);
        mDatas.add(bean2);
        mDatas.add(bean3);
        FileBean customView = new FileBean(2, 0, "CustomView");
        FileBean graphics2D = new FileBean(2001, 2, "Graphics2D", Graphics2DActivity.class.getName());
        FileBean doubleCache = new FileBean(2002, 2, "DoubleCache", DoubleCacheActivity.class.getName());
        FileBean drawer = new FileBean(2003, 2, "Drawer", DrawerActivity.class.getName());
        FileBean sgb = new FileBean(2004, 2, "Sgb", SgbActivity.class.getName());
        FileBean view = new FileBean(2005, 2, "View", ViewActivity.class.getName());
        FileBean viewGroup = new FileBean(2006, 2, "ViewGroup", ViewGroupActivity.class.getName());
        FileBean flowLayout = new FileBean(2007, 2, "FlowLayout", FlowLayoutActivity.class.getName());
        mDatas.add(customView);
        mDatas.add(graphics2D);
        mDatas.add(doubleCache);
        mDatas.add(drawer);
        mDatas.add(sgb);
        mDatas.add(view);
        mDatas.add(viewGroup);
        mDatas.add(flowLayout);
        FileBean recyclerView = new FileBean(3, 0, "RecyclerView");
        FileBean recycler = new FileBean(3001, 3, "Recycler", RecyclerActivity.class.getName());
        FileBean waterFall = new FileBean(3002, 3, "WaterFall", WaterfallActivity.class.getName());
        mDatas.add(recyclerView);
        mDatas.add(recycler);
        mDatas.add(waterFall);
        return mDatas;
    }
}
