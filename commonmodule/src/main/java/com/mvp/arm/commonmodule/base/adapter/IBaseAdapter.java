package com.mvp.arm.commonmodule.base.adapter;

import android.view.View;

import java.util.List;

/**
 * 适配器基类
 *
 * @author Gjm
 * @date 2018/5/22
 */
public interface IBaseAdapter<E> {
    /**
     * 添加单条数据
     */
    void add(E e);

    /**
     * 添加多条数据
     */
    void addAll(List<? extends E> list);

    /**
     * 设置指定位置的数据
     */
    void set(int position,E e);

    /**
     * 移除单条数据
     */
    void remove(E e);

    /**
     * 移除多条数据
     */
    void removeAll(List<? extends E> list);

    /**
     * 清空数据
     */
    void clear();

    /**
     * 返回集合数据
     */
    List<E> getAdapterData();

    /**
     * 获取convertView
     */
    View getConvertView();

    /**
     * 视图绑定
     */
    void onBindView(IViewHolder holder, final int position, E e);
}
