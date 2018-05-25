package com.mvp.arm.commonmodule.util.imageloader.loader.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.mvp.arm.commonmodule.util.imageloader.config.GlobalConfig;
import com.mvp.arm.commonmodule.util.imageloader.config.ImageConfig;
import com.mvp.arm.commonmodule.util.imageloader.config.PriorityMode;
import com.mvp.arm.commonmodule.util.imageloader.config.ScaleMode;
import com.mvp.arm.commonmodule.util.imageloader.loader.BaseImageLoader;
import com.mvp.arm.commonmodule.util.imageloader.utils.ImageUtils;
import com.orhanobut.logger.Logger;

/**
 * Description: GlideLoader
 * @author Zijin
 * @date 2017/7/31
 */

public class GlideLoader extends BaseImageLoader {
    private static final String TAG = "GlideLoader";

    @Override
    public void init(Context context, int cacheSizeInM,boolean isInternalCD) {
        GlideBuilder builder = new GlideBuilder();
        if (isInternalCD) {
            builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSizeInM * 1024 * 1024));
        } else {
            builder.setDiskCache(new ExternalPreferredCacheDiskCacheFactory(context, cacheSizeInM * 1024 * 1024));
        }
    }

    @Override
    public void load(ImageConfig config) {
        GlideRequest builder;
        if (config.isGif()) {
            builder = GlideApp.with(config.getContext()).asGif();
        } else {
            builder = GlideApp.with(config.getContext()).asBitmap();
            if (config.isAsBitmap()) {
                getRequestBuilder(config, builder).into(new SimpleTarget<Bitmap>(config.getWidth(), config.getHeight()) {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        config.getBitmapListener().onSuccess(resource);
                    }

                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });
                return;
            }
        }

        getRequestBuilder(config, builder);

        //设置占位图
        if (config.shouldSetPlaceHolder()) {
            builder.placeholder(config.getPlaceHolderResId());
        }

        //设置缩略图
        if (config.getThumbnail() != 0) {
            builder.thumbnail(config.getThumbnail());
        }

        //设置错误显示
        if (config.getErrorResId() > 0) {
            builder.error(config.getErrorResId());
        }

        //设置缓存策略
        if (config.getDiskCacheStrategy() != null) {
            builder.diskCacheStrategy(config.getDiskCacheStrategy());
        }

        //设置缩放模式
        switch (config.getScaleMode()){
            case ScaleMode.CENTER_CROP:
                builder.centerCrop();
                break;
            case ScaleMode.CENTER_INSIDE:
                builder.centerInside();
                break;
            case ScaleMode.FIT_CENTER:
            default:
                builder.fitCenter();
                break;
        }

        //设置加载优先级
        builder.priority(getPriority(config));

        //显示图片
        if (config.getTarget() instanceof ImageView) {
            builder.into((ImageView) config.getTarget());
        }
    }

    @Override
    public void pause() {
        GlideApp.with(GlobalConfig.getContext()).pauseRequestsRecursive();
    }

    @Override
    public void resume() {
        GlideApp.with(GlobalConfig.getContext()).resumeRequestsRecursive();
    }

    @Override
    public void clearDiskCache() {
        GlideApp.get(GlobalConfig.getContext()).clearDiskCache();
    }

    @Override
    public void clearMemory() {
        GlideApp.get(GlobalConfig.getContext()).clearMemory();
    }

    @Override
    public void clearMemoryCache(View view) {
        GlideApp.with(GlobalConfig.getContext()).clear(view);
    }

    @Override
    public boolean isCached(String url) {
        return false;
    }

    @Override
    public void trimMemory(int level)    {
        GlideApp.get(GlobalConfig.getContext()).trimMemory(level);
    }

    @Override
    public void clearAllMemoryCaches() {
        clearDiskCache();
        clearMemory();
    }

    /**
     * 获取加载优先级
     */
    private Priority getPriority(ImageConfig config) {
        switch (config.getPriorityMode()) {
            case PriorityMode.PRIORITY_LOW:
                return Priority.LOW;
            case PriorityMode.PRIORITY_NORMAL:
                return Priority.NORMAL;
            case PriorityMode.PRIORITY_HIGH:
                return Priority.HIGH;
            case PriorityMode.PRIORITY_IMMEDIATE:
            default:
                return Priority.IMMEDIATE;
        }
    }

    /**
     * 构建GlideRequest
     */
    private GlideRequest getRequestBuilder(ImageConfig config, GlideRequest requestBuilder) {
        if (!TextUtils.isEmpty(config.getUrl())) {
            requestBuilder = requestBuilder.load(ImageUtils.appendUrl(config.getUrl()));
            Logger.d(TAG, "getUrl: " + config.getUrl());
        } else if (!TextUtils.isEmpty(config.getFilePath())) {
            requestBuilder = requestBuilder.load(ImageUtils.appendUrl(config.getFilePath()));
            Logger.d(TAG, "getFilePath: " + config.getFilePath());
        } else if (!TextUtils.isEmpty(config.getContentProvider())) {
            requestBuilder = requestBuilder.load(Uri.parse(config.getContentProvider()));
            Logger.d(TAG, "getContentProvider: " + config.getContentProvider());
        } else if (config.getResId() > 0) {
            requestBuilder = requestBuilder.load(config.getResId());
            Logger.d(TAG, "getResId: " + config.getResId());
        } else if (config.getFile() != null) {
            requestBuilder = requestBuilder.load(config.getFile());
            Logger.d(TAG, "getFile: " + config.getFile());
        } else if (!TextUtils.isEmpty(config.getAssertspath())) {
            requestBuilder = requestBuilder.load(config.getAssertspath());
            Logger.d(TAG, "getAssertspath: " + config.getAssertspath());
        } else if (!TextUtils.isEmpty(config.getRawPath())) {
            requestBuilder = requestBuilder.load(config.getRawPath());
            Logger.d(TAG, "getRawPath: " + config.getRawPath());
        }
        return requestBuilder;
    }

    @Override
    protected Bitmap getSourceBitmap(Context context, String url) throws Exception{
        return GlideApp.with(context).asBitmap()
                .load(url)
                .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .get();
    }
}
