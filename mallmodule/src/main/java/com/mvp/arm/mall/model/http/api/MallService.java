package com.mvp.arm.mall.model.http.api;

import com.mvp.arm.commonmodule.base.bean.Repo;

import io.reactivex.Flowable;
import retrofit2.http.GET;

/**
 * desc
 *
 * @author Gjm
 * @date 2018/5/25
 */
public interface MallService {
    @GET(ServiceRoutes.INDEX)
    Flowable<Repo<String>> platformIndex();
}
