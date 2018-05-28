package com.mvp.arm.main.model.http.api;

import com.mvp.arm.commonmodule.base.bean.Repo;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * api service
 *
 * @author Gjm
 * @date 2018/5/25
 */
public interface MainService {
    @GET(ServiceRoutes.INDEX)
    Flowable<Repo<String>> platformIndex();
}
