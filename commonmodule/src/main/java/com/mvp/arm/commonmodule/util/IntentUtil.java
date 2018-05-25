package com.mvp.arm.commonmodule.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;

/**
 * Description: Intent工具类
 * @author Zijin
 * @date 2017/7/27
 */

public final class IntentUtil {
    /**
     * 跳转系统拨号界面
     */
    public static void jumpCallPhone(Context context, CharSequence phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent it = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(it);
    }

    /**
     * 跳转发送邮件
     *
     * @param mailAddress 邮箱地址
     * @param subject     主题
     * @param body        内容
     */
    public static void jumpEmail(Context context, CharSequence mailAddress, CharSequence subject, CharSequence body) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{mailAddress.toString()});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, body);
        try {
            context.startActivity(Intent.createChooser(i, "发送邮件..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "您尚未安装邮件客户端", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 选择浏览器
     */
    public static void jumpWebChoose(Context context, CharSequence url) {
        if (url == null) {
            return;
        }
        String urlStr = url.toString();
        if (!urlStr.startsWith("http")) {
            urlStr = "http://" + urlStr;
        }
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(urlStr);
        intent.setData(content_url);
        context.startActivity(Intent.createChooser(intent, "请选择一款浏览器"));
    }

    /**
     * 跳转系统短信界面
     */
    public static void jumpSms(Context context, CharSequence content) {
        Intent it = new Intent(Intent.ACTION_VIEW);
        it.setType("vnd.android-dir/mms-sms");
        it.putExtra("sms_body", content);
        context.startActivity(it);
    }

    /**
     * 打开指定QQ聊天页
     *
     * @param qqNum QQ号
     */
    public static void jumpQQ(Context ctx, String qqNum) {
        if (!TextUtils.isEmpty(qqNum)) {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qqNum;
            try {
                ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            } catch (RuntimeException e) {
                ToastManager.get().showMessage(ctx, "请先安装QQ程序");
                e.printStackTrace();
            }
        }
    }

    /**
     * 跳转到skype指定联系人界面
     *
     * @param skypeAct skype账号
     */
    public static void jumpSkype(Context ctx, String skypeAct) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("skype:" + skypeAct + "?chat&topic=" + AppUtil.getAppName(ctx)));
        intent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

    /**
     * 打开指定的app
     */
    public static void jumpApplication(Context ctx, String appPackageName) {
        Intent intent = ctx.getPackageManager().getLaunchIntentForPackage(appPackageName);
        ctx.startActivity(intent);
    }

    /**
     * 发起添加群流程。
     *
     * @param key 由官网生成的key
     * @return 返回true表示呼起手Q成功，返回false表示呼起失败
     */
    public static boolean joinQQGroup(Context ctx, String key) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        try {
            ctx.startActivity(intent);
            return true;
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持
            return false;
        }
    }

    /**
     * 跳转到singleTask类
     */
    public static void jumpSingleTask(Context ctx, Class cls, Bundle bundle) {
        Intent intent = new Intent(ctx, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        ctx.startActivity(intent);
    }

    /**
     * 安装APK文件
     *
     * @param file 针对7.0可能出现的权限问题，解决方案
     *             1、application的oncreate()添加
     *             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//7.0权限问题
     *             StrictMode.VmPolicy.Builder vmBuilder = new StrictMode.VmPolicy.Builder();
     *             StrictMode.setVmPolicy(vmBuilder.build());
     *             }
     *             2、利用FileProvider，进行权限共享
     *             if(Build.VERSION.SDK_INT > Build.VERSION_CODES.N){//判读版本是否在7.0以上
     *             apkUri = FileProvider.getUriForFile(ctx, ctx.getPackageName() + ".fileprovider", file);
     *             intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
     *             }
     */
    public static void installApk(Context ctx, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri apkUri = Uri.fromFile(file);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        ctx.startActivity(intent);
    }
}
