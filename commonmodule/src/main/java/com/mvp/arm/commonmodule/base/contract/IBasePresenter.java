package com.mvp.arm.commonmodule.base.contract;

/**
 * mvp base presenter
 *
 * @author Gjm
 * @date 2018/5/23
 */
public interface IBasePresenter<V> extends IContract.IPresenter{
    /**
     * attach
     */
    void attach(V v);

    /**
     * detach
     */
    void detach();
}
