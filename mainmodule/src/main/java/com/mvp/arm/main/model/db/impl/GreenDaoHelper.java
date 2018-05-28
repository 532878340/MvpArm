package com.mvp.arm.main.model.db.impl;

import com.mvp.arm.commonmodule.di.annotation.scope.ApplicationScope;
import com.mvp.arm.main.model.db.DbHelper;

import javax.inject.Inject;

/**
 * greendao helper
 * @author Gjm
 * @date 2018/01/12
 */

@ApplicationScope
public class GreenDaoHelper implements DbHelper {
    @Inject
    public GreenDaoHelper() {
    }
}
