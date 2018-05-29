package com.mvp.arm.main.contract;

import com.mvp.arm.commonmodule.base.contract.IBasePresenter;
import com.mvp.arm.commonmodule.base.contract.IBaseView;

/**
 * desc
 *
 * @author Gjm
 * @date 2018/5/29
 */
public interface IndexContract {
    interface IIndexView extends IBaseView{
    }

    interface IIndexPresenter extends IBasePresenter<IIndexView>{
        void getInfo();
    }
}
