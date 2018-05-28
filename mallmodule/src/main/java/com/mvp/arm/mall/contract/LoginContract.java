package com.mvp.arm.mall.contract;

import com.mvp.arm.commonmodule.base.contract.IBasePresenter;
import com.mvp.arm.commonmodule.base.contract.IBaseView;

/**
 * $desc$
 *
 * @author Gjm
 * @date 2018/5/25
 */
public interface LoginContract{
    interface ILoginView extends IBaseView{

    }

    interface ILoginPresenter extends IBasePresenter<ILoginView>{
        void login();
    }
}
