package com.mvp.arm.commonmodule.module;

import android.content.Context;

import java.util.List;

/**
 * 模块配置
 *
 * @author Gjm
 * @date 2018/5/28
 */
public interface IModuleConfig {
    /**
     * 注入application lifecycle
     */
    void injectApplicationLifecycle(Context context, List<IApplicationLifecycle> lifecycles);
}
