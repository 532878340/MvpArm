package com.mvp.arm.main.model.http.impl;

import com.mvp.arm.commonmodule.base.bean.Repo;
import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.main.model.http.HttpHelper;
import com.mvp.arm.main.model.http.api.MainService;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * retrofit helper
 *
 * @author Gjm
 * @date 2018/01/12
 */

@ApplicationScope
public class RetrofitHelper implements HttpHelper {
    private MainService mMallService;

    @Inject
    public RetrofitHelper(MainService apiService) {
        this.mMallService = apiService;
    }

    @Override
    public Flowable<Repo<Object>> platformIndex() {
        return mMallService.platformIndex();
    }
}
