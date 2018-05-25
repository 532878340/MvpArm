package com.mvp.arm.commonmodule.base.subscriber;

import android.text.TextUtils;

import com.mvp.arm.commonmodule.base.bean.Repo;
import com.mvp.arm.commonmodule.base.constant.MessageMode;
import com.mvp.arm.commonmodule.base.contract.IBaseView;
import com.orhanobut.logger.Logger;

import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.HttpException;

/**
 * rx subscriber
 *
 * @author Gjm
 * @date 2018/1/16
 */
public abstract class CommonSubscriber<T> extends ResourceSubscriber<Repo<T>> {
    /**
     * view
     */
    protected IBaseView mView;
    /**
     * 错误提示
     */
    private String mErrorMsg;
    /**
     * 是否显示错误布局
     */
    private boolean showErrorState;

    public CommonSubscriber(IBaseView view) {
        this(view,false);
    }

    public CommonSubscriber(IBaseView view, boolean showErrorState) {
        this(view,null,showErrorState);
    }

    public CommonSubscriber(IBaseView view, String errorMsg, boolean showErrorState) {
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.showErrorState = showErrorState;
    }

    @Override
    public void onNext(Repo<T> repo) {
        if(mView == null){
            return;
        }

        Logger.d(repo);

        if(repo.isOk()){
            mView.onStatusMain();
            onSuccess(repo.getData());
        }else if(showErrorState){
            mView.onStatusError();
        }else{
            mView.showMessage(MessageMode.SNACKBAR,repo.getDescription());
            mView.onStatusMain();
            onIllegal(repo);
        }
    }

    @Override
    public void onError(Throwable e) {
        if(mView == null){
            return;
        }

        if (!TextUtils.isEmpty(mErrorMsg)) {
            mView.showMessage(MessageMode.SNACKBAR,mErrorMsg);
        } else if (e instanceof HttpException) {
            mView.showMessage(MessageMode.SNACKBAR,"数据加载失败ヽ(≧Д≦)ノ");
        } else {
            mView.showMessage(MessageMode.SNACKBAR,"未知错误ヽ(≧Д≦)ノ");
            Logger.d(e.toString());
        }
        if (showErrorState) {
            mView.onStatusError();
        }else{
            mView.onStatusMain();
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onSuccess(T resp);

    /**
     * 子类有需要可单独处理
     */
    protected void onIllegal(Repo<T> repo){
    }

    public IBaseView getBindView(){
        return mView;
    }
}
