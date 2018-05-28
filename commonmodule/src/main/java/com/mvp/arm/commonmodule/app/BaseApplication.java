package com.mvp.arm.commonmodule.app;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

import com.mvp.arm.commonmodule.di.component.AppComponent;
import com.mvp.arm.commonmodule.di.component.DaggerAppComponent;
import com.mvp.arm.commonmodule.di.module.AppModule;
import com.mvp.arm.commonmodule.manager.ActivityContainer;
import com.mvp.arm.commonmodule.service.InitializeService;
import com.mvp.arm.commonmodule.util.imageloader.ImageLoader;

/**
 * Application对象
 *
 * @author Gjm
 * @date 2018/5/22
 */
public class BaseApplication extends Application{
    private static BaseApplication sInstance;
    private ApplicationDelegate mAppDelegate;

    private AppComponent sAppComponent;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mAppDelegate = new ApplicationDelegate();
        mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        InitializeService.init(this);
        initDagger();
        mAppDelegate.onCreate(this);
    }

    public static synchronized BaseApplication get(){
        return sInstance;
    }

    private void initDagger(){
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(sInstance))
                .build();
    }

    /**
     * 获取全局AppComponent
     */
    public final AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.trimMemory(level);
        mAppDelegate.onTerminate(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        ImageLoader.clearAllMemoryCaches();
    }

    /**
     * 退出应用
     */
    public void exit() {
        ActivityContainer.get().finishAll();
    }
}
