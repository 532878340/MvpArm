package com.mvp.arm.main.model.http;


import com.mvp.arm.commonmodule.base.bean.Repo;

import io.reactivex.Flowable;

/**
 * retrofit helper
 * @author Gjm
 * @date 2018/01/12
 */

public interface HttpHelper {
    Flowable<Repo<String>> platformIndex();
}
