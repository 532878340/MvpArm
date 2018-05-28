package com.mvp.arm.main.di.component;

import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.commonmodule.di.component.AppComponent;
import com.mvp.arm.main.di.module.DataModule;
import com.mvp.arm.main.di.module.HttpModule;
import com.mvp.arm.main.model.MainRepositoryManager;

import dagger.Component;

/**
 * MainComponent
 *
 * @author Gjm
 * @date 2018/5/25
 */
@ApplicationScope
@Component(dependencies = AppComponent.class,modules = {HttpModule.class, DataModule.class})
public interface MainComponent {
    MainRepositoryManager getRepositoryManager();
}
