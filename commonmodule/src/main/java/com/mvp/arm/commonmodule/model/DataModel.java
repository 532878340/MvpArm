package com.mvp.arm.commonmodule.model;

import android.support.annotation.NonNull;

import com.mvp.arm.commonmodule.base.bean.Repo;
import com.mvp.arm.commonmodule.base.subscriber.CommonSubscriber;
import com.mvp.arm.commonmodule.manager.constants.Configs;
import com.mvp.arm.commonmodule.util.TransformUtils;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;

/**
 * model层
 *
 * @author Gjm
 * @date 2018/5/23
 */
public class DataModel {
    @Inject
    public DataModel(){
    }

    /**
     * 通用网络请求
     * @param flowable 请求flowable
     * @param subscriber 响应
     * @param <T> 响应类型
     */
    public <T> void performRequest(@NonNull Flowable<Repo<T>> flowable, CommonSubscriber<T> subscriber) {
        flowable.throttleFirst(Configs.THROTTLE_DELAY, TimeUnit.MILLISECONDS)
                .compose(subscriber.getBindView().bindToLife())
                .compose(TransformUtils.flowableIOToMain())
                .subscribe(subscriber);
    }
}
