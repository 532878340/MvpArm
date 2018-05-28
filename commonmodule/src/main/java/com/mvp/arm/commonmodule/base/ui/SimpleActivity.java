package com.mvp.arm.commonmodule.base.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.mvp.arm.commonmodule.R;
import com.mvp.arm.commonmodule.manager.ActivityContainer;
import com.mvp.arm.commonmodule.util.ActivityUtils;
import com.mvp.arm.commonmodule.util.DensityHelper;
import com.mvp.arm.commonmodule.view.TitleBar;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * 顶层无MVP activity
 *
 * @author Gjm
 * @date 2018/5/23
 */
public abstract class SimpleActivity extends RxAppCompatActivity {
    protected Context mCtx;

    protected TitleBar mTitleBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DensityHelper.get().active(this);
        setContentView(R.layout.base_container);

        ActivityContainer.get().add(this);

        mCtx = this;
        if (getLayoutRes() != 0) {
            View.inflate(mCtx, getLayoutRes(), findViewById(R.id.container));
        }

        mTitleBar = findViewById(R.id.titleBar);
        onViewCreated();
        initViewOrData();
    }

    /**
     * 资源布局
     */
    @LayoutRes
    protected abstract int getLayoutRes();

    /**
     * 操作视图
     */
    protected abstract void initViewOrData();

    /**
     * view创建完成
     */
    protected void onViewCreated() {
    }

    @Override
    protected void onDestroy() {
        ActivityUtils.fixInputMethodManagerLeak(this);
        ActivityContainer.get().remove(this);
        super.onDestroy();
    }

    /**
     * 获取参数
     */
    protected String getBundleValue(String key) {
        return getIntent().getExtras() == null ? null : getIntent().getExtras().getString(key);
    }
}
