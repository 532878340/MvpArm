package com.mvp.arm.main.delegate;

import com.mvp.arm.commonmodule.base.presenter.BasePresenter;
import com.mvp.arm.commonmodule.base.ui.BaseFragment;
import com.mvp.arm.main.di.component.DaggerFragmentComponent;
import com.mvp.arm.main.di.component.FragmentComponent;

/**
 * 每个module 各自实现自己的rootfragment
 *
 * @author Gjm
 * @date 2018/5/29
 */
public abstract class RootFragment<P extends BasePresenter> extends BaseFragment<FragmentComponent,P> {
    @Override
    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent
                .builder()
                .mainComponent(MainDelegate.getComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }
}
