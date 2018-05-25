package com.mvp.arm.commonmodule.di.module;

import android.app.Application;

import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.commonmodule.model.DataManager;
import com.mvp.arm.commonmodule.model.db.DbHelper;
import com.mvp.arm.commonmodule.model.db.impl.GreenDaoHelper;
import com.mvp.arm.commonmodule.model.http.HttpHelper;
import com.mvp.arm.commonmodule.model.http.impl.RetrofitHelper;

import dagger.Module;
import dagger.Provides;

/**
 * 全局AppModule
 * @author Gjm
 * @date 2018/01/12
 */

@Module
public class AppModule {
    private Application mApp;

    public AppModule(Application app) {
        this.mApp = app;
    }

    @Provides
    @ApplicationScope
    Application provideApp(){
        return mApp;
    }

    @ApplicationScope
    @Provides
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper){
        return retrofitHelper;
    }

    @ApplicationScope
    @Provides
    DbHelper provideDBHelper(GreenDaoHelper greenDaoHelper){
        return greenDaoHelper;
    }

    @ApplicationScope
    @Provides
    DataManager provideDataManager(HttpHelper httpHelper, DbHelper dbHelper){
        return new DataManager(httpHelper,dbHelper);
    }
}
