package com.mvp.arm.mall.di.component;

import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.commonmodule.di.component.AppComponent;
import com.mvp.arm.mall.di.module.DataModule;
import com.mvp.arm.mall.di.module.HttpModule;
import com.mvp.arm.mall.model.MallRepositoryManager;

import dagger.Component;

/**
 * MallComponent
 *
 * @author Gjm
 * @date 2018/5/25
 */
@ApplicationScope
@Component(dependencies = AppComponent.class,modules = {HttpModule.class, DataModule.class})
public interface MallComponent {
    MallRepositoryManager getRepositoryManager();
}
