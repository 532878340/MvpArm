package com.mvp.arm.mainmodule;

import android.widget.TextView;
import com.mvp.arm.commonmodule.base.ui.SimpleActivity;
import butterknife.BindView;

/**
 * 主页面
 *
 * @author Gjm
 * @date 2018/5/21
 */
public class MainActivity extends SimpleActivity {
    @BindView(R2.id.tvBtn)
    TextView tvBtn;

    @Override
    protected int getLayoutRes() {
        return R.layout.actvity_main;
    }

    @Override
    protected void initViewOrData() {
        mTitleBar.setTitle("主页");
        tvBtn.setText("这是loading");
    }
}
