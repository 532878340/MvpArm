package com.mvp.arm.commonmodule.util;

import android.util.SparseArray;
import android.view.View;

/**
 * ListView GridView通用工具
 */
public class ViewHolderUtil {
	@SuppressWarnings("unchecked")
	public static <T extends View> T get(View view, int id){
		SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
	}
}
