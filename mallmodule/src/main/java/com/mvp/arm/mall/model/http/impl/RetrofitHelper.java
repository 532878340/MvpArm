package com.mvp.arm.mall.model.http.impl;

import com.mvp.arm.commonmodule.base.bean.Repo;
import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.mall.model.http.HttpHelper;
import com.mvp.arm.mall.model.http.api.MallService;

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
    private MallService mMallService;

    @Inject
    public RetrofitHelper(MallService apiService) {
        this.mMallService = apiService;
    }

    @Override
    public Flowable<Repo<String>> platformIndex() {
        return mMallService.platformIndex();
    }
}
