package com.mvp.arm.commonmodule.util;

import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 线程调度工具类
 * @author Zijin
 * @date 2017/7/25
 */

public class TransformUtils {
    /**
     * 常用请求，线程调度 flowable
     */
    public static <T> FlowableTransformer<T,T> flowableIOToMain(){
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 全部 Schedulers.io() flowable
     */
    public static <T> FlowableTransformer<T, T> flowableAllIO() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }

    /**
     * 常用请求，线程调度 observable
     */
    public static <T> ObservableTransformer<T,T> observableIOToMain(){
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 全部 Schedulers.io() observable
     */
    public static <T> ObservableTransformer<T,T> observableAllIO(){
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
    }
}
