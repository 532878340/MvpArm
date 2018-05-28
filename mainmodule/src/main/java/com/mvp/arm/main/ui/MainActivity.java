package com.mvp.arm.main.ui;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.mvp.arm.commonmodule.base.adapter.BaseFragmentPagerAdapter;
import com.mvp.arm.commonmodule.base.ui.SimpleActivity;
import com.mvp.arm.main.bean.sub.MainTabEntity;
import com.mvp.arm.main.ui.fragment.IndexFragment;
import com.mvp.arm.mainmodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页面
 *
 * @author Gjm
 * @date 2018/5/21
 */
public class MainActivity extends SimpleActivity {
    private CommonTabLayout mTabLayout;
    private ViewPager mViewPager;

    private final String[] ARR_TITLES = {"首页","商城","我的"};
    private final int[] ARR_SELECTICON = { R.drawable.tab_index_pre, R.drawable.tab_invest_pre, R.drawable.tab_account_pre };
    private final int[] ARR_UNSELECTICON = { R.drawable.tab_index, R.drawable.tab_invest, R.drawable.tab_account };

    @Override
    protected int getLayoutRes() {
        return R.layout.actvity_main;
    }

    @Override
    protected void initViewOrData() {
        mTabLayout = findViewById(R.id.commonTabLayout);
        mViewPager = findViewById(R.id.viewPager);

        initTabLayout();
        initViewPager();
    }

    private void initTabLayout(){
        ArrayList<CustomTabEntity> list = new ArrayList<>();
        for (int i = 0;i < ARR_TITLES.length ; i ++){
            list.add(new MainTabEntity(ARR_TITLES[i],ARR_SELECTICON[i],ARR_UNSELECTICON[i]));
        }

        mTabLayout.setTabData(list);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    private void initViewPager(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IndexFragment());
        fragments.add(new IndexFragment());
        fragments.add(new IndexFragment());

        mViewPager.setAdapter(new BaseFragmentPagerAdapter(getSupportFragmentManager(),fragments){
            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return ARR_TITLES[position];
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
