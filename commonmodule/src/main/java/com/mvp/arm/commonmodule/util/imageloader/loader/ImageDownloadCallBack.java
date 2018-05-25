package com.mvp.arm.commonmodule.util.imageloader.loader;

import android.graphics.Bitmap;

/**
 * Description:图片下载回调
 *
 * @author Zijin
 * @date 2017/8/1
 */

public interface ImageDownloadCallBack {
    /**
     * 下载成功
     */
    void onDownLoadSuccess(Bitmap bitmap);

    /**
     * 下载失败
     */
    void onDownLoadFailed();
}
