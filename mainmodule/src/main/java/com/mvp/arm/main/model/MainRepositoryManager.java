package com.mvp.arm.main.model;

import com.mvp.arm.commonmodule.base.bean.Repo;
import com.mvp.arm.main.model.db.DbHelper;
import com.mvp.arm.main.model.http.HttpHelper;

import io.reactivex.Flowable;

/**
 * model manager
 * @author Gjm
 * @date 2018/01/12
 */
public class MainRepositoryManager implements HttpHelper,DbHelper {
    private HttpHelper mHttpHelper;
    private DbHelper mDbHelper;

    public MainRepositoryManager(HttpHelper httpHelper, DbHelper dbHelper) {
        this.mHttpHelper = httpHelper;
        this.mDbHelper = dbHelper;
    }

    @Override
    public Flowable<Repo<Object>> platformIndex() {
        return mHttpHelper.platformIndex();
    }
}
