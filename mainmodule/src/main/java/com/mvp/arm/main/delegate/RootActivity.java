package com.mvp.arm.main.delegate;

import com.mvp.arm.commonmodule.base.presenter.BasePresenter;
import com.mvp.arm.commonmodule.base.ui.BaseActivity;
import com.mvp.arm.main.di.component.ActivityComponent;
import com.mvp.arm.main.di.component.DaggerActivityComponent;

/**
 * 每个module 各自实现自己的rootactivity
 *
 * @author Gjm
 * @date 2018/5/28
 */
public abstract class RootActivity<P extends BasePresenter> extends BaseActivity<ActivityComponent,P>{
    @Override
    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent
                .builder()
                .mainComponent(MainDelegate.getComponent())
                .activityModule(getActivityModule())
                .build();
    }
}
