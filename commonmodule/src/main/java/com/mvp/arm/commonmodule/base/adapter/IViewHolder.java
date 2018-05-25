package com.mvp.arm.commonmodule.base.adapter;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * @author Zijin
 * @date 2017/7/13
 */

public interface IViewHolder {
    /**
     * 根据id返回指定view
     */
    <V extends View> V getView(@IdRes int id);
}
