package com.mvp.arm.commonmodule.di.annotation.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Zijin on 2017/8/3.
 * Email: info@zijinqianbao.com
 */

@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiUrl {
}
