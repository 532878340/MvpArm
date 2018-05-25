package com.mvp.arm.commonmodule.util.imageloader.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.mvp.arm.commonmodule.util.CloseUtils;
import com.mvp.arm.commonmodule.util.imageloader.config.GlobalConfig;
import com.mvp.arm.commonmodule.util.imageloader.config.ImageConfig;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * Description:图片工具类
 *
 * @author Zijin
 * @date 2017/8/1
 */

public class ImageUtils {
    public static ImageConfig.BitmapListener getBitmapListenerProxy(ImageConfig.BitmapListener listener) {
        return (ImageConfig.BitmapListener) Proxy.newProxyInstance(ImageConfig.class.getClassLoader(), listener.getClass().getInterfaces(), (proxy, method, args) -> {
            runOnUIThread(() -> {
                try {
                    method.invoke(listener,args);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            return null;
        });
    }

    public static void runOnUIThread(Runnable runnable) {
        GlobalConfig.getMainHandler().post(runnable);
    }

    /**
     * 追加url，返回完整url
     */
    public static String appendUrl(String url) {
        String newUrl = url;
        if (TextUtils.isEmpty(newUrl)) {
            return newUrl;
        }
        boolean hasHost = url.contains("http:") || url.contains("https:");
        if (!hasHost) {
            if (!TextUtils.isEmpty(GlobalConfig.BASE_URL)) {
                newUrl = GlobalConfig.BASE_URL + url;
            }
        }

        return newUrl;
    }

    /**
     * 保存Bitmap为图片
     * @param context
     * @param bmp
     * @param fileName
     * @param isSetMediaStore 是否加入系统图库
     */
    public static File saveImageToGallery(Context context, Bitmap bmp,String fileName,boolean isSetMediaStore) {
        // 首先保存图片 注意小米手机必须这样获得public绝对路径
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();

        File appDir = new File(file ,fileName);
        if (!appDir.exists()) {
            appDir.mkdirs();
        }
        fileName = System.currentTimeMillis() + ".jpg";
        File currentFile = new File(appDir, fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(currentFile);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            CloseUtils.close(fos);
        }

        if(isSetMediaStore){
            setMediaDtore(context,fileName,currentFile);
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,Uri.fromFile(new File(currentFile.getPath()))));

        return currentFile;
    }

    /**
     * 加入到系统图库
     * @param fileName
     */
    public static void setMediaDtore(Context context,String fileName,File file){
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
