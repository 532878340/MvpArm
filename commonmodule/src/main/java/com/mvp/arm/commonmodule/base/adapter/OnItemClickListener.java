package com.mvp.arm.commonmodule.base.adapter;

import android.view.View;

/**
 * RecycleView item
 * @author Gjm
 */
public interface OnItemClickListener<E>{
    /**
     * 点击事件
     */
    void onItemClick(View view, int position,E e);

    /**
     * 长按
     */
    boolean onItemLongClick(View view, int position,E e);
}