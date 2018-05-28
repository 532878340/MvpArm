package com.mvp.arm.commonmodule.app;

import android.app.Application;
import android.content.Context;

import com.mvp.arm.commonmodule.module.IApplicationLifecycle;
import com.mvp.arm.commonmodule.module.IModuleConfig;
import com.mvp.arm.commonmodule.module.ManifestParser;

import java.util.ArrayList;
import java.util.List;

/**
 * application对象代理
 *
 * @author Gjm
 * @date 2018/5/28
 */
public class ApplicationDelegate implements IApplicationLifecycle {
    private List<IModuleConfig> mModuleList;
    private List<IApplicationLifecycle> mLifecycles;

    public ApplicationDelegate() {
        mLifecycles = new ArrayList<>();
    }

    @Override
    public void attachBaseContext(Context base) {
        mModuleList = new ManifestParser(base).parse();
        for (IModuleConfig config : mModuleList){
            config.injectApplicationLifecycle(base,mLifecycles);
        }

        for (IApplicationLifecycle lifecycle : mLifecycles){
            lifecycle.attachBaseContext(base);
        }
    }

    @Override
    public void onCreate(Application application) {
        for (IApplicationLifecycle lifecycle : mLifecycles){
            lifecycle.onCreate(application);
        }
    }

    @Override
    public void onTerminate(Application application) {
        for (IApplicationLifecycle lifecycle : mLifecycles){
            lifecycle.onTerminate(application);
        }
    }
}
