package com.mvp.arm.commonmodule.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.github.moduth.blockcanary.BlockCanary;
import com.mvp.arm.commonmodule.R;
import com.mvp.arm.commonmodule.app.BaseApplication;
import com.mvp.arm.commonmodule.manager.constants.Configs;
import com.mvp.arm.commonmodule.util.AppBlockCanaryContext;
import com.mvp.arm.commonmodule.util.SystemUtil;
import com.mvp.arm.commonmodule.util.imageloader.ImageLoader;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;

import zlc.season.rxdownload3.core.DownloadConfig;

/**
 * 初始化Service
 * @author Gjm
 * @date 2018/01/12
 */

public class InitializeService extends IntentService{
    private static final String ACTION_INIT = "initApplication";

    /**
     * 全局初始化
     */
    public static void init(Context context){
        syncInit(context);
        asyncInit(context);
    }

    /**
     * 常用初始化
     */
    private static void syncInit(Context context){
        //图片加载
        ImageLoader.init(context);
        //multidex
        MultiDex.install(context);
        //ARouter
        if(Configs.DEBUG_ENABLE){
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(BaseApplication.get());
    }

    /**
     * 子线程初始化
     */
    private static void asyncInit(Context context){
        Intent intent = new Intent(context,InitializeService.class);
        intent.setAction(ACTION_INIT);
        context.startService(intent);
    }

    public InitializeService() {
        super("InitializeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null && ACTION_INIT.equals(intent.getAction())){
            initApplicationAsync();
        }
    }

    /**
     * 初始化SmartRefresh
     */
    private void initSmartRefresh(){
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            //全局设置主题颜色
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            ////指定为经典Header，默认是 贝塞尔雷达Header
            return new ClassicsHeader(context).setSpinnerStyle(SpinnerStyle.Translate);
        });

        //设置全局的Footer构建器 经典Footer，默认是 BallPulseFooter
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context)
                .setSpinnerStyle(SpinnerStyle.Translate));
    }

    /**
     * 异步初始化：
     * 日志
     * 错误收集
     * 内存泄漏
     * 过渡绘制检测
     * rx下载
     */
    private void initApplicationAsync(){
        initLogger();
        initBugly();
        initSmartRefresh();
        initLeakCanary();
        initBlockCanary();
        initRxDownload();
    }

    /**
     * 初始化日志
     */
    private void initLogger(){
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return Configs.DEBUG_ENABLE;
            }
        });
    }

    /**
     * 初始化Bugly
     */
    private void initBugly(){
        Context ctx = getApplicationContext();
        String packageName = ctx.getPackageName();
        String processName = SystemUtil.getProcessName(android.os.Process.myPid());
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(ctx);
        strategy.setUploadProcess(processName == null || processName.equals(packageName));
        CrashReport.initCrashReport(BaseApplication.get(), Configs.APP_ID_BUGLY, Configs.DEBUG_ENABLE, strategy);
    }

    /**
     * 初始化内存泄漏检测
     */
    private void initLeakCanary(){
        if(LeakCanary.isInAnalyzerProcess(BaseApplication.get())){
            return;
        }
        LeakCanary.install(BaseApplication.get());
    }

    /**
     * 初始化过渡绘制检测
     */
    private void initBlockCanary(){
        BlockCanary.install(BaseApplication.get(),new AppBlockCanaryContext());
    }

    /**
     * 初始化RxDownload
     */
    private void initRxDownload(){
        DownloadConfig.Builder builder = DownloadConfig.Builder
                .Companion
                .create(BaseApplication.get())
                .enableAutoStart(true)
                .enableService(true)
                .enableNotification(true);
        DownloadConfig.INSTANCE.init(builder);
    }
}
