package com.mvp.arm.commonmodule.base.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by Zijin on 2017/7/13.
 * RecycleView ViewHolder基类
 */

public class BaseRecycleHolder extends RecyclerView.ViewHolder implements IViewHolder {
    private SparseArray<View> mViewArray;

    public BaseRecycleHolder(View itemView) {
        super(itemView);
        mViewArray = new SparseArray<>();
    }

    @Override
    public <V extends View> V getView(@IdRes int id) {
        View view = mViewArray.get(id);
        if (view == null) {
            view = itemView.findViewById(id);

            mViewArray.put(id, view);
        }
        return (V) view;
    }
}
