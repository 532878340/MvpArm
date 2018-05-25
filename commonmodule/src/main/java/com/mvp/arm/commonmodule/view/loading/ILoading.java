package com.mvp.arm.commonmodule.view.loading;

/**
 * @author Zijin
 * @date 2017/7/24
 * Email:info@zijinqianbao.com
 */

public interface ILoading {
    /**
     * 初始化
     */
    void init();

    /**
     * 正在加载
     */
    void loading();

    /**
     * 加载成功
     */
    void success();

    /**
     * 加载失败
     */
    void fail();

    /**
     * 状态改变监听
     * @param status
     */
    void onStatusChange(Status status);

    /**
     * 获取当前状态
     * @return
     */
    Status getStatus();

    /**
     * 设置加载状态
     */
    void setStatus(Status status);
}
