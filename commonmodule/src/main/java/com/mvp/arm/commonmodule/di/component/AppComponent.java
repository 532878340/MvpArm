package com.mvp.arm.commonmodule.di.component;

import android.app.Application;

import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.commonmodule.di.module.AppModule;
import com.mvp.arm.commonmodule.di.module.HttpModule;
import com.mvp.arm.commonmodule.model.DataManager;

import dagger.Component;

/**
 * 全局AppComponent
 * @author Gjm
 * @date 2018/01/12
 */

@ApplicationScope
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    /**
     * 提供App的context
     */
    Application getAppContext();

    /**
     * 提供数据
     */
    DataManager getDataManager();
}
