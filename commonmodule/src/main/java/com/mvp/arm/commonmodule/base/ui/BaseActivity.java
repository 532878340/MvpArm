package com.mvp.arm.commonmodule.base.ui;

import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.mvp.arm.commonmodule.R2;
import com.mvp.arm.commonmodule.app.BaseApplication;
import com.mvp.arm.commonmodule.base.constant.MessageMode;
import com.mvp.arm.commonmodule.base.constant.Status;
import com.mvp.arm.commonmodule.base.contract.IBaseView;
import com.mvp.arm.commonmodule.base.presenter.BasePresenter;
import com.mvp.arm.commonmodule.di.component.ActivityComponent;
import com.mvp.arm.commonmodule.di.component.DaggerActivityComponent;
import com.mvp.arm.commonmodule.di.module.ActivityModule;
import com.mvp.arm.commonmodule.util.SnackBarUtils;
import com.mvp.arm.commonmodule.util.ToastManager;
import com.mvp.arm.commonmodule.view.loading.LoadingView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * MVP activity基类
 *
 * @author Gjm
 * @date 2018/5/23
 */
public abstract class BaseActivity<P extends BasePresenter> extends SimpleActivity implements IBaseView {
    @BindView(R2.id.loading)
    protected LoadingView mLoadingView;

    /**
     * 记录当前状态
     */
    protected Status mStatus = Status.SUCCESS;

    @Inject
    protected P mPresenter;

    /**
     * 获取activity Component
     */
    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    /**
     * 获取activity module
     */
    @NonNull
    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    @CallSuper
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if(mPresenter != null){
            mPresenter.attach(this);
        }

        if(initLoadingUI()){
            onStatusInit();
            initRequestLoading();
            mLoadingView.setOnRetryListener(() -> initRequestLoading());
        }
    }

    /**
     * 注入
     */
    protected void initInject(){
//        getActivityComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        if(mPresenter != null){
            mPresenter.detach();
        }
        super.onDestroy();
    }

    @Override
    public void onStatusInit() {
        if(mStatus != Status.INIT){
            mStatus = Status.INIT;
            mLoadingView.init();
        }
    }

    @Override
    public void onStatusLoading() {
        if(mStatus != Status.LOADING){
            mStatus = Status.LOADING;
            mLoadingView.loading();
        }
    }

    @Override
    public void onStatusMain() {
        if(mStatus != Status.SUCCESS){
            mStatus = Status.SUCCESS;
            mLoadingView.success();
        }
    }

    @Override
    public void onStatusError() {
        if(mStatus != Status.FAIL){
            mStatus = Status.FAIL;
            mLoadingView.fail();
        }
    }

    @Override
    public void showMessage(MessageMode mode, CharSequence message) {
        switch (mode){
            case SNACKBAR:
                SnackBarUtils.showSnackBar(mLoadingView,message);
                break;
            case TOAST:
                ToastManager.get().showMessage(this,message);
                break;
        }
    }

    @Override
    public void useNightMode(boolean night) {
        AppCompatDelegate.setDefaultNightMode(night ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
        recreate();
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindToLifecycle();
    }

    @Override
    public boolean initLoadingUI() {
        return true;
    }

    @Override
    public void initRequestLoading() {
    }
}
