package com.mvp.arm.mall.di.component;

import android.support.v4.app.FragmentActivity;

import com.mvp.arm.commonmodule.di.annotation.scope.ActivityScope;
import com.mvp.arm.commonmodule.di.module.ActivityModule;
import com.mvp.arm.mall.MallActivity;

import dagger.Component;

/**
 * 模块component
 *
 * @author Gjm
 * @date 2018/5/25
 */
@ActivityScope
@Component(dependencies = MallComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    FragmentActivity getActivity();

    void inject(MallActivity activity);
}
