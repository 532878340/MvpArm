package com.mvp.arm.commonmodule.di.module;

import android.app.Activity;

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
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @ActivityScope
    Activity provideActivity(){
        return mActivity;
    }

    @Provides
    @ActivityScope
    RxPermissions provideRxPermissions(){
        return new RxPermissions(mActivity);
    }
}
