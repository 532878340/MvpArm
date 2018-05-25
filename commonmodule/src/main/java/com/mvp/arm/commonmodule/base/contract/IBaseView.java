package com.mvp.arm.commonmodule.base.contract;

import android.app.Activity;

import com.mvp.arm.commonmodule.base.constant.MessageMode;
import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * mvp base view
 *
 * @author Gjm
 * @date 2018/5/23
 */
public interface IBaseView extends IContract.IView{
    /**
     * 初始化加载
     */
    void onStatusInit();

    /**
     * 加载中
     */
    void onStatusLoading();

    /**
     * 显示主界面
     */
    void onStatusMain();

    /**
     * 加载出错
     */
    void onStatusError();

    /**
     * 展示信息
     */
    void showMessage(MessageMode mode, CharSequence message);

    Activity getActivity();

    /**
     * 绑定生命周期
     */
    <T> LifecycleTransformer<T> bindToLife();

    /**
     * 是否使用夜间模式
     */
    void useNightMode(boolean night);

    /**
     * 初始化页面是否显示Loading页面
     */
    boolean initLoadingUI();

    /**
     * 初始化请求操作
     */
    void initRequestLoading();
}
