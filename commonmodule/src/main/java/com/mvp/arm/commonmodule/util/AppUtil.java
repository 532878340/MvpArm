package com.mvp.arm.commonmodule.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 *
 * @author Zijin
 * @date 2017/7/26
 */

public class AppUtil {
    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取系统版本信息
     * 版本名：info.versionName
     * 版本号：info.versionCode
     */
    public static PackageInfo getPackageInfo(Context context) {
        String packageName = context.getApplicationContext().getPackageName();
        try {
            return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
