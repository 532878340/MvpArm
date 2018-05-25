package com.mvp.arm.commonmodule.base.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * ListAdapter holder
 *
 * @author Gjm
 * @date 2018/5/22
 */
public class BaseListHolder implements IViewHolder {
    private SparseArray<View> mViewArray;

    private View mItemView;

    public BaseListHolder(View itemView) {
        mItemView = itemView;
        mViewArray = new SparseArray<>();
    }

    @Override
    public <V extends View> V getView(int id) {
        View view = mViewArray.get(id);
        if (view == null) {
            view = mItemView.findViewById(id);

            mViewArray.put(id, view);
        }
        return (V) view;
    }
}
