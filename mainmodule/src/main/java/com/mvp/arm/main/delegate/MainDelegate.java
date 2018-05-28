package com.mvp.arm.main.delegate;

import android.app.Application;
import android.content.Context;

import com.mvp.arm.commonmodule.app.BaseApplication;
import com.mvp.arm.commonmodule.module.IApplicationLifecycle;
import com.mvp.arm.commonmodule.module.IModuleConfig;
import com.mvp.arm.main.di.component.DaggerMainComponent;
import com.mvp.arm.main.di.component.MainComponent;
import com.mvp.arm.main.di.module.DataModule;
import com.mvp.arm.main.di.module.HttpModule;

import java.util.List;

/**
 * MainDelegate
 *
 * @author Gjm
 * @date 2018/5/28
 */
public class MainDelegate implements IModuleConfig,IApplicationLifecycle {
    private static MainComponent sComponent;

    @Override
    public void injectApplicationLifecycle(Context context, List<IApplicationLifecycle> lifecycles) {
        lifecycles.add(this);
    }

    @Override
    public void attachBaseContext(Context base) {
    }

    @Override
    public void onCreate(Application application) {
        sComponent = DaggerMainComponent.builder()
                .appComponent(BaseApplication.get().getAppComponent())
                .httpModule(new HttpModule())
                .dataModule(new DataModule())
                .build();
    }

    @Override
    public void onTerminate(Application application) {
    }

    public static MainComponent getComponent(){
        return sComponent;
    }
}
