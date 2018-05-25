package com.mvp.arm.commonmodule.di.component;

import android.support.v4.app.Fragment;

import com.mvp.arm.commonmodule.di.annotation.scope.FragmentScope;
import com.mvp.arm.commonmodule.di.module.FragmentModule;

import dagger.Component;

/**
 * fragment component
 * @author Gjm
 * @date 2018/01/12
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    /**
     * 获取fragment
     * @return
     */
    Fragment getFragment();
}
