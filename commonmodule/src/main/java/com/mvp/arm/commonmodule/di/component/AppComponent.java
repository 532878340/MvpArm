package com.mvp.arm.commonmodule.di.component;

import android.app.Application;

import com.mvp.arm.commonmodule.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 全局AppComponent
 * @author Gjm
 * @date 2018/01/12
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    /**
     * 提供App的context
     */
    Application getAppContext();
}
