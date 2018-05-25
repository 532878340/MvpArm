package com.mvp.arm.commonmodule.base.presenter;

import com.mvp.arm.commonmodule.base.bean.Repo;
import com.mvp.arm.commonmodule.base.contract.IBaseView;
import com.mvp.arm.commonmodule.base.subscriber.CommonSubscriber;
import com.mvp.arm.commonmodule.model.DataManager;
import com.mvp.arm.commonmodule.model.DataModel;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * model manager
 *
 * @author Gjm
 * @date 2018/5/23
 */
public class RxPresenter<V extends IBaseView> extends BasePresenter<V>{
    /**
     * 数据源
     */
    protected DataManager mDataManager;

    @Inject
    protected DataModel mDataModel;

    public RxPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    /**
     * 常用请求
     */
    protected <T> void performRequest(Flowable<Repo<T>> flowable, CommonSubscriber<T> subscriber) {
        mDataModel.performRequest(flowable,subscriber);
    }

    /**
     * 请求 带进度条
     */
    protected <T> void performRequestLoading(Flowable<Repo<T>> flowable, CommonSubscriber<T> subscriber) {
        getView().onStatusLoading();
        performRequest(flowable, subscriber);
    }
}
