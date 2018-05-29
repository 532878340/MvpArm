package com.mvp.arm.main.ui.fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mvp.arm.main.R;
import com.mvp.arm.main.contract.IndexContract;
import com.mvp.arm.main.delegate.RootFragment;
import com.mvp.arm.main.presenter.IndexPresenter;

/**
 * 主页
 *
 * @author Gjm
 * @date 2018/5/28
 */
public class IndexFragment extends RootFragment<IndexPresenter> implements IndexContract.IIndexView {
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_index;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initViewOrData() {
        mContainer.findViewById(R.id.label).setOnClickListener(v -> {
//            mPresenter.getInfo();
            ARouter.getInstance().build("/mall/main").navigation();
        });
    }

    @Override
    public boolean initLoadingUI() {
        return false;
    }
}
