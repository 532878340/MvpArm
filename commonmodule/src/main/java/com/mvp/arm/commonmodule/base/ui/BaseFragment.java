package com.mvp.arm.commonmodule.base.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.arm.commonmodule.R;
import com.mvp.arm.commonmodule.base.constant.MessageMode;
import com.mvp.arm.commonmodule.base.constant.Status;
import com.mvp.arm.commonmodule.base.contract.IBaseView;
import com.mvp.arm.commonmodule.base.presenter.BasePresenter;
import com.mvp.arm.commonmodule.di.module.FragmentModule;
import com.mvp.arm.commonmodule.util.SnackBarUtils;
import com.mvp.arm.commonmodule.util.ToastManager;
import com.mvp.arm.commonmodule.view.loading.LoadingView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

/**
 * MVP fragment基类
 *
 * @author Gjm
 * @date 2018/5/29
 */
public abstract class BaseFragment<C,P extends BasePresenter> extends SimpleFragment implements IBaseView{
    protected LoadingView mLoadingView;

    @Inject
    protected P mPresenter;

    /**
     * 记录当前状态
     */
    protected Status mStatus = Status.SUCCESS;

    /**
     * 注入
     */
    protected void initInject(){
    }

    /**
     * 获取activity Component
     */
    protected C getFragmentComponent(){
        return null;
    }

    /**
     * 获取activity module
     */
    @NonNull
    protected FragmentModule getFragmentModule(){
        return new FragmentModule(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        mLoadingView = view.findViewById(R.id.loading);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initInject();
        if(mPresenter != null){
            mPresenter.attach(this);
        }

        if(initLoadingUI()){
            onStatusInit();
            initRequestLoading();
            mLoadingView.setOnRetryListener(() -> initRequestLoading());
        }

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        if(mPresenter != null){
            mPresenter.detach();
        }
        super.onDestroyView();
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
                ToastManager.get().showMessage(mContext,message);
                break;
        }
    }

    @Override
    public FragmentActivity getFragmentActivity() {
        return getActivity();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindToLifecycle();
    }

    @Override
    public void useNightMode(boolean night) {
        AppCompatDelegate.setDefaultNightMode(night ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }

    @Override
    public boolean initLoadingUI() {
        return true;
    }

    @Override
    public void initRequestLoading() {
    }
}
