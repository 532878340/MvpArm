package com.mvp.arm.main.di.component;

import android.support.v4.app.FragmentActivity;

import com.mvp.arm.commonmodule.di.annotation.scope.ActivityScope;
import com.mvp.arm.commonmodule.di.module.ActivityModule;
import com.mvp.arm.main.ui.MainActivity;

import dagger.Component;

/**
 * 模块component
 *
 * @author Gjm
 * @date 2018/5/25
 */
@ActivityScope
@Component(dependencies = MainComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    FragmentActivity getActivity();

    void inject(MainActivity activity);
}
