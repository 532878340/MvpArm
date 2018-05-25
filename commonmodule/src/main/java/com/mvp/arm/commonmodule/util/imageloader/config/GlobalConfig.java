package com.mvp.arm.commonmodule.util.imageloader.config;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.mvp.arm.commonmodule.manager.constants.Configs;
import com.mvp.arm.commonmodule.util.imageloader.loader.ILoader;
import com.mvp.arm.commonmodule.util.imageloader.loader.glide.GlideLoader;

/**
 * Description: ImageLoader配置
 * @date 2017/7/31
 */

public class GlobalConfig {
    /**
     * 图片基础目录
     */
    public static final String BASE_URL = Configs.BASE_URL;

    private static Context mContext;

    /**
     * lrucache 最大值
     */
    public static int mCacheMaxSize;

    public static void init(Context context, int cacheSizeInM, boolean isInternalCD) {
        mContext = context;
        mCacheMaxSize = cacheSizeInM;

        getLoader().init(context, cacheSizeInM,isInternalCD);
    }

    private static ILoader mLoader;

    /**
     * 实际Loader加载器
     */
    public static ILoader getLoader() {
        if (mLoader == null) {
            mLoader = new GlideLoader();
        }
        return mLoader;
    }

    private static Handler mHandler;

    /**
     * 返回主线程Handler
     */
    public static Handler getMainHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }

        return mHandler;
    }

    public static Context getContext() {
        return mContext;
    }
}
