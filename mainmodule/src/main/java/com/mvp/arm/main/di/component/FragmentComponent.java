package com.mvp.arm.main.di.component;

import android.support.v4.app.Fragment;

import com.mvp.arm.commonmodule.di.annotation.scope.FragmentScope;
import com.mvp.arm.commonmodule.di.module.FragmentModule;

import dagger.Component;

/**
 * FragmentComponent
 *
 * @author Gjm
 * @date 2018/5/28
 */
@FragmentScope
@Component(dependencies = MainComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Fragment getFragment();
}
