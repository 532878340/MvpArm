package com.mvp.arm.commonmodule.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * 全局AppModule
 * @author Gjm
 * @date 2018/01/12
 */

@Module
public class AppModule {
    private Application mApp;

    public AppModule(Application app) {
        this.mApp = app;
    }

    @Provides
    @Singleton
    Application provideApp(){
        return mApp;
    }
}
