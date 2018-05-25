package com.mvp.arm.commonmodule.di.component;


import android.app.Activity;

import com.mvp.arm.commonmodule.di.annotation.scope.ActivityScope;
import com.mvp.arm.commonmodule.di.module.ActivityModule;

import dagger.Component;

/**
 * activity component
 * @author Gjm
 * @date 2018/01/12
 */
@ActivityScope
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();
}
