package com.mvp.arm.commonmodule.util.imageloader;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mvp.arm.commonmodule.R;
import com.mvp.arm.commonmodule.util.imageloader.config.GlobalConfig;
import com.mvp.arm.commonmodule.util.imageloader.config.ImageConfig;
import com.mvp.arm.commonmodule.util.imageloader.loader.ILoader;
import com.mvp.arm.commonmodule.util.imageloader.loader.ImageDownloadCallBack;

/**
 * Description: 图片加载
 * @author Zijin
 * @date 2017/7/31
 */

@SuppressWarnings("unused")
public class ImageLoader {
    private static Context mContext;

    /**
     * 默认最大缓存
     */
    private static final int CACHE_IMAGE_SIZE = 150;

    public static void init(final Context context) {
        init(context, CACHE_IMAGE_SIZE);
    }

    public static void init(final Context context, int cacheSizeInM) {
        init(context, cacheSizeInM, true);
    }

    /**
     * @param context        上下文
     * @param cacheSizeInM   Glide默认磁盘缓存最大容量250MB
     * @param isInternalCD   true 磁盘缓存到应用的内部目录 / false 磁盘缓存到外部存
     */
    public static void init(Context context, int cacheSizeInM, boolean isInternalCD) {
        mContext = context;
        GlobalConfig.init(context, cacheSizeInM, isInternalCD);
    }

    /**
     * 获取加载器
     */
    private static ILoader getImageLoader() {
        return GlobalConfig.getLoader();
    }

    /**
     * 系统清理内存
     */
    public static void trimMemory(int level) {
        getImageLoader().trimMemory(level);
    }

    /**
     * 清除所有缓存
     */
    public static void clearAllMemoryCaches() {
        getImageLoader().clearAllMemoryCaches();
    }

    /**
     * 暂停请求
     */
    public static void pauseRequests() {
        getImageLoader().pause();
    }

    /**
     * 恢复请求
     */
    public static void resumeRequests() {
        getImageLoader().resume();
    }

    /**
     * 清理内存缓存视图
     */
    public static void clearMemoryCache(View view) {
        getImageLoader().clearMemoryCache(view);
    }

    /**
     * 清理磁盘缓存
     */
    public static void clearDiskCache() {
        getImageLoader().clearDiskCache();
    }

    /**
     * 清理内存
     */
    public static void clearMemory() {
        getImageLoader().clearMemory();
    }

    /**
     * 图片保存到相册
     */
    public static void saveImageIntoGallery(String url, String fileName, ImageDownloadCallBack callBack) {
        getImageLoader().saveImageIntoGallery(mContext, url, false, fileName, callBack);
    }

    /**
     * 默认显示image方法
     */
    public static void displayImage(Context context, String url, View view) {
        new ImageConfig.ConfigBuilder(context)
                .url(url)
                .error(R.drawable.icon_default)
                .placeHolder(R.drawable.icon_default)
                .setDiskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }
}
