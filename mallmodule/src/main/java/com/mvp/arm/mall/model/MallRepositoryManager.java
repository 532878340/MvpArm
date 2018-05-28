package com.mvp.arm.mall.model;

import com.mvp.arm.commonmodule.base.bean.Repo;
import com.mvp.arm.mall.model.db.DbHelper;
import com.mvp.arm.mall.model.http.HttpHelper;

import io.reactivex.Flowable;

/**
 * model manager
 * @author Gjm
 * @date 2018/01/12
 */
public class MallRepositoryManager implements HttpHelper,DbHelper{
    private HttpHelper mHttpHelper;
    private DbHelper mDbHelper;

    public MallRepositoryManager(HttpHelper httpHelper, DbHelper dbHelper) {
        this.mHttpHelper = httpHelper;
        this.mDbHelper = dbHelper;
    }

    @Override
    public Flowable<Repo<String>> platformIndex() {
        return mHttpHelper.platformIndex();
    }
}
