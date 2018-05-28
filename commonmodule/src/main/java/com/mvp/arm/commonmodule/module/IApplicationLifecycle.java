package com.mvp.arm.commonmodule.module;

import android.app.Application;
import android.content.Context;

/**
 * application生命周期
 *
 * @author Gjm
 * @date 2018/5/28
 */
public interface IApplicationLifecycle {
    void attachBaseContext(Context base);

    void onCreate(Application application);

    void onTerminate(Application application);
}
