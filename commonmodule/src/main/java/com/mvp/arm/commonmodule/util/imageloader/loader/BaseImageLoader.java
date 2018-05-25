package com.mvp.arm.commonmodule.util.imageloader.loader;

import android.content.Context;
import android.graphics.Bitmap;

import com.mvp.arm.commonmodule.util.imageloader.utils.ImageUtils;

import java.io.File;

/**
 * Description: 加载器基类
 *
 * @author Zijin
 * @date 2017/8/1
 * Email: info@zijinqianbao.com
 */

public abstract class BaseImageLoader implements ILoader {
    /**
     * 下载图片
     *
     * @param context         上下文
     * @param url             url
     * @param isSetMediaStore 是否添加到系统图库
     * @param fileName        保存的文件名
     * @param callBack        回调
     */
    @Override
    public void saveImageIntoGallery(Context context, String url, boolean isSetMediaStore, String fileName, ImageDownloadCallBack callBack) {
        new Thread() {
            @Override
            public void run() {
                super.run();

                Bitmap bitmap = null;
                File saveFile = null;

                try {
                    bitmap = getSourceBitmap(context, url);

                    if (bitmap != null) {
                        saveFile = ImageUtils.saveImageToGallery(context, bitmap, fileName, isSetMediaStore);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bitmap != null && saveFile != null && saveFile.exists()) {
                        callBack.onDownLoadSuccess(bitmap);
                    } else {
                        callBack.onDownLoadFailed();
                    }
                }
            }
        }.start();
    }

    /**
     * 获取网络Bitmap
     */
    protected abstract Bitmap getSourceBitmap(Context context, String url) throws Exception;
}
