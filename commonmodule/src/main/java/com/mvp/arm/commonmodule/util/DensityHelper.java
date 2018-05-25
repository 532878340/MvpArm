package com.mvp.arm.commonmodule.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.mvp.arm.commonmodule.util.sharedpreference.SharedPreferenceManager;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Field;

/**
 * 屏幕适配工具类
 *
 * @author Gjm
 * @date 2018/3/1
 */
public final class DensityHelper {
    private static final String TAG = "DensityHelper";

    /**
     * 设计图宽度
     */
    private static final float DESIGN_WIDTH = 750;
    /**
     * xdpi
     */
    private static final String KEY_DENSITY_XDPI = "key_density_xdpi";

    private DensityHelper(){
    }

    private static final class Holder{
        private static final DensityHelper INSTANCE = new DensityHelper();
    }

    public static DensityHelper get(){
        return Holder.INSTANCE;
    }

    /**
     * 目标xdpi
     */
    private float mTargetXdpi;

    /**
     * 激活适配方式
     */
    public void active(Context context){
        if(mTargetXdpi == 0){
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            mTargetXdpi = size.x / DESIGN_WIDTH * 72f;
        }

        hookDisplayXdpi(context, mTargetXdpi);
    }

    /**
     * 取消适配方式
     */
    public void inActive(Context context){
        float originXdpi = SharedPreferenceManager.get().getFloat(KEY_DENSITY_XDPI);

        if(originXdpi != 0){
            hookDisplayXdpi(context,originXdpi);
        }
    }

    private void hookDisplayXdpi(Context context,float xdpi) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();

        Logger.d(TAG, "当前xdpi为: " + metrics.xdpi + " 目标xdpi为：" + xdpi);
        if(metrics.xdpi == xdpi){
            return;
        }

        //保留原始xdpi尺寸
        if(SharedPreferenceManager.get().getFloat(KEY_DENSITY_XDPI) == 0){
            SharedPreferenceManager.get().setFloat(KEY_DENSITY_XDPI,metrics.xdpi);
        }

        //解决MIUI更改框架导致的MIUI7 + Android5.1.1上出现的失效问题(以及极少数基于这部分miui去掉art然后置入xposed的手机)
        if ("MiuiResources".equals(resources.getClass().getSimpleName()) || "XResources".equals(resources.getClass().getSimpleName())) {
            try {
                Field field = Resources.class.getDeclaredField("mTmpMetrics");
                field.setAccessible(true);
                metrics = (DisplayMetrics) field.get(resources);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        metrics.xdpi = xdpi;
    }
}
