package com.mvp.arm.mall.presenter;

import android.util.Log;

import com.mvp.arm.commonmodule.base.presenter.RxPresenter;
import com.mvp.arm.mall.contract.LoginContract;
import com.mvp.arm.mall.model.MallRepositoryManager;

import javax.inject.Inject;

/**
 * desc
 *
 * @author Gjm
 * @date 2018/5/25
 */
public class LoginPresenter extends RxPresenter<LoginContract.ILoginView,MallRepositoryManager> implements LoginContract.ILoginPresenter{
    private static final String TAG = "LoginPresenter";

    @Inject
    public LoginPresenter(MallRepositoryManager dataManager) {
        super(dataManager);
    }

    @Override
    public void login() {
        Log.d(TAG,"登录中......");

        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }
}
