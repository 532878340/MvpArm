package com.mvp.arm.mall.di.module;

import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.mall.model.MallRepositoryManager;
import com.mvp.arm.mall.model.db.DbHelper;
import com.mvp.arm.mall.model.db.impl.GreenDaoHelper;
import com.mvp.arm.mall.model.http.HttpHelper;
import com.mvp.arm.mall.model.http.impl.RetrofitHelper;

import dagger.Module;
import dagger.Provides;

/**
 * desc
 *
 * @author Gjm
 * @date 2018/5/28
 */
@Module
public class DataModule {
    @Provides
    @ApplicationScope
    MallRepositoryManager provideRepositoryManager(HttpHelper httpHelper, DbHelper dbHelper){
        return new MallRepositoryManager(httpHelper,dbHelper);
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
