package com.mvp.arm.main.bean.sub;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * tab
 *
 * @author Gjm
 * @date 2018/5/28
 */
public class MainTabEntity implements CustomTabEntity {
    private String mTitle;
    private int mSelectIcon;
    private int mUnSelectIcon;

    public MainTabEntity(String title, int selectIcon, int unSelectIcon) {
        this.mTitle = title;
        this.mSelectIcon = selectIcon;
        this.mUnSelectIcon = unSelectIcon;
    }

    @Override
    public String getTabTitle() {
        return mTitle;
    }

    @Override
    public int getTabSelectedIcon() {
        return mSelectIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return mUnSelectIcon;
    }
}
