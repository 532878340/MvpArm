package com.mvp.arm.main.di.module;

import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.main.model.MainRepositoryManager;
import com.mvp.arm.main.model.db.DbHelper;
import com.mvp.arm.main.model.db.impl.GreenDaoHelper;
import com.mvp.arm.main.model.http.HttpHelper;
import com.mvp.arm.main.model.http.impl.RetrofitHelper;

import dagger.Module;
import dagger.Provides;

/**
 * DataModule
 *
 * @author Gjm
 * @date 2018/5/28
 */
@Module
public class DataModule {
    @Provides
    @ApplicationScope
    MainRepositoryManager provideRepositoryManager(HttpHelper httpHelper, DbHelper dbHelper){
        return new MainRepositoryManager(httpHelper,dbHelper);
    }

    @Provides
    @ApplicationScope
    HttpHelper provideHttpHelper(RetrofitHelper helper){
        return helper;
    }

    @Provides
    @ApplicationScope
    DbHelper provideDbHelper(GreenDaoHelper helper){
        return helper;
    }
}
