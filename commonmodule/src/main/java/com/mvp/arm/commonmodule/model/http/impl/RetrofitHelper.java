package com.mvp.arm.commonmodule.model.http.impl;

import com.mvp.arm.commonmodule.base.bean.Repo;
import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.commonmodule.model.http.HttpHelper;
import com.mvp.arm.commonmodule.model.http.api.ApiService;

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
    private ApiService mApiService;

    @Inject
    public RetrofitHelper(ApiService apiService) {
        this.mApiService = apiService;
    }

    @Override
    public Flowable<Repo<String>> platformIndex() {
        return mApiService.platformIndex();
    }
}
