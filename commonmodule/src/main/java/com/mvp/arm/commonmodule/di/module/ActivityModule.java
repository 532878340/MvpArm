package com.mvp.arm.commonmodule.di.module;

import android.support.v4.app.FragmentActivity;

import com.mvp.arm.commonmodule.di.annotation.scope.ActivityScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

/**
 * activity module
 * @author Gjm
 * @date 2018/01/12
 */

@Module
public class ActivityModule{
    private FragmentActivity mActivity;

    public ActivityModule(FragmentActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    FragmentActivity provideActivity(){
        return mActivity;
    }

    @Provides
    @ActivityScope
    RxPermissions provideRxPermissions(){
        return new RxPermissions(mActivity);
    }
}
