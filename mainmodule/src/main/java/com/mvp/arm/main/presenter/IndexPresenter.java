package com.mvp.arm.main.presenter;

import com.mvp.arm.commonmodule.base.presenter.RxPresenter;
import com.mvp.arm.commonmodule.base.subscriber.CommonSubscriber;
import com.mvp.arm.main.contract.IndexContract;
import com.mvp.arm.main.model.MainRepositoryManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * desc
 *
 * @author Gjm
 * @date 2018/5/29
 */
public class IndexPresenter extends RxPresenter<IndexContract.IIndexView,MainRepositoryManager> implements IndexContract.IIndexPresenter{
    @Inject
    public IndexPresenter(MainRepositoryManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getInfo() {
        performRequestLoading(mDataManager.platformIndex(), new CommonSubscriber<Object>(getView()) {
            @Override
            public void onSuccess(Object resp) {
            }
        });
    }
}
