package com.mvp.arm.commonmodule.base.presenter;

import com.mvp.arm.commonmodule.base.contract.IBasePresenter;
import com.mvp.arm.commonmodule.base.contract.IBaseView;

import java.lang.ref.WeakReference;

/**
 * Presenter基类
 *
 * @author Gjm
 * @date 2018/5/23
 */
public class BasePresenter<V extends IBaseView> implements IBasePresenter<V>{
    /**
     * 弱应用 view
     */
    private WeakReference<V> mWeakReference;

    private V mView;

    public V getView(){
        if(mWeakReference != null){
            mView = mWeakReference.get();
        }
        return mView;
    }

    @Override
    public void attach(V v) {
        mWeakReference = new WeakReference<>(v);
    }

    @Override
    public void detach() {
        if(mWeakReference != null){
            mWeakReference.clear();
            mWeakReference = null;
        }
    }
}
