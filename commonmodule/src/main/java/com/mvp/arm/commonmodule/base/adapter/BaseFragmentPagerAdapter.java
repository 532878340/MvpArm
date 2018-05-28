package com.mvp.arm.commonmodule.base.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentPagerAdapter基类
 * @author Gjm
 * @date 2018/01/12
 */
public class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public BaseFragmentPagerAdapter(FragmentManager fm, @NonNull List<? extends Fragment> fragments) {
        super(fm);
        mFragmentList.addAll(fragments);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }
}
