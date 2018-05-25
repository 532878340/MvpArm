package com.mvp.arm.commonmodule.di.module;

import android.support.v4.app.Fragment;

import com.mvp.arm.commonmodule.di.annotation.scope.FragmentScope;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

/**
 * fragment module
 * @author Gjm
 * @date 2018/01/12
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        this.mFragment = fragment;
    }

    @Provides
    @FragmentScope
    Fragment provideFragment(){
        return mFragment;
    }

    @Provides
    @FragmentScope
    RxPermissions provideRxPermissions(){
        return new RxPermissions(mFragment.getActivity());
    }
}
