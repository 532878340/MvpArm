package com.mvp.arm.mall;

import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mvp.arm.commonmodule.util.imageloader.ImageLoader;
import com.mvp.arm.mall.contract.LoginContract;
import com.mvp.arm.mall.delegate.RootActivity;
import com.mvp.arm.mall.presenter.LoginPresenter;

/**
 * 商城
 *
 * @author Gjm
 * @date 2018/5/21
 */
@Route(path = "/mall/main")
public class MallActivity extends RootActivity<LoginPresenter> implements LoginContract.ILoginView{
    @Override
    protected int getLayoutRes() {
        return R.layout.act_mall;
    }

    @Override
    protected void initViewOrData() {
        ImageView imageView = findViewById(R.id.img);
        ImageLoader.displayImage(this,"http://imgsrc.baidu.com/forum/w=580/sign=1588b7c5d739b6004dce0fbfd9503526/7bec54e736d12f2eb97e1a464dc2d56285356898.jpg",imageView);
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initRequestLoading() {
        mPresenter.login();
    }
}
