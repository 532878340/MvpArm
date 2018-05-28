package com.mvp.arm.mall.delegate;

import android.app.Application;
import android.content.Context;

import com.mvp.arm.commonmodule.app.BaseApplication;
import com.mvp.arm.commonmodule.module.IApplicationLifecycle;
import com.mvp.arm.commonmodule.module.IModuleConfig;
import com.mvp.arm.mall.di.component.DaggerMallComponent;
import com.mvp.arm.mall.di.component.MallComponent;
import com.mvp.arm.mall.di.module.DataModule;
import com.mvp.arm.mall.di.module.HttpModule;

import java.util.List;

/**
 * MallDelegate
 *
 * @author Gjm
 * @date 2018/5/28
 */
public class MallDelegate implements IModuleConfig,IApplicationLifecycle {
    private static MallComponent sComponent;

    @Override
    public void injectApplicationLifecycle(Context context, List<IApplicationLifecycle> lifecycles) {
        lifecycles.add(this);
    }

    @Override
    public void attachBaseContext(Context base) {
    }

    @Override
    public void onCreate(Application application) {
        sComponent = DaggerMallComponent.builder()
                .appComponent(BaseApplication.get().getAppComponent())
                .httpModule(new HttpModule())
                .dataModule(new DataModule())
                .build();
    }

    @Override
    public void onTerminate(Application application) {
    }

    public static MallComponent getComponent(){
        return sComponent;
    }
}
