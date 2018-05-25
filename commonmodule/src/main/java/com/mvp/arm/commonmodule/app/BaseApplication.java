package com.mvp.arm.commonmodule.app;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.mvp.arm.commonmodule.di.component.AppComponent;
import com.mvp.arm.commonmodule.di.component.DaggerAppComponent;
import com.mvp.arm.commonmodule.di.module.AppModule;
import com.mvp.arm.commonmodule.di.module.HttpModule;
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

    private static AppComponent sAppComponent;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        InitializeService.init(this);
    }

    public static synchronized BaseApplication get(){
        return sInstance;
    }

    /**
     * 获取全局AppComponent
     */
    public static AppComponent getAppComponent() {
        if (sAppComponent == null) {
            sAppComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(sInstance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return sAppComponent;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ImageLoader.trimMemory(level);
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
