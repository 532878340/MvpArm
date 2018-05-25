package com.mvp.arm.commonmodule.model.http.api;

import com.mvp.arm.commonmodule.base.bean.Repo;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * api接口
 *
 * @author Gjm
 * @date 2018/01/12
 */
public interface ApiService {
    @GET(ApiServiceRoutes.INDEX)
    Flowable<Repo<String>> platformIndex();
}
