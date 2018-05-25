package com.mvp.arm.commonmodule.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * adapter父类
 *
 * @author Gjm
 * @date 2018/5/22
 */
public abstract class BaseListAdapter<E> extends BaseAdapter implements IBaseAdapter<E> {
    protected List<E> mList = new ArrayList<>();
    protected Context mCtx;
    protected LayoutInflater mLayoutInflater;
    private int mLayoutId;

    public BaseListAdapter(Context context,List<E> list,int layoutId){
        this.mCtx = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        if(list != null){
            mList.addAll(list);
        }
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public E getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseListHolder holder;
        if(convertView == null){
            convertView = mLayoutId == 0 ? getConvertView() : mLayoutInflater.inflate(mLayoutId,parent,false);
            holder = new BaseListHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (BaseListHolder) convertView.getTag();
        }

        onBindView(holder,position,getItem(position));
        return convertView;
    }

    @Override
    public View getConvertView() {
        return new View(mCtx);
    }

    @Override
    public void add(E e) {
        if(e != null){
            mList.add(e);
            notifyDataSetChanged();
        }
    }

    @Override
    public void addAll(List<? extends E> list) {
        if(list != null){
            mList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void set(int position, E e) {
        if(e != null){
            mList.set(position,e);
            notifyDataSetChanged();
        }
    }

    @Override
    public void remove(E e) {
        if(e != null){
            mList.remove(e);
            notifyDataSetChanged();
        }
    }

    @Override
    public void removeAll(List<? extends E> list) {
        if(list != null){
            mList.removeAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public List<E> getAdapterData() {
        return mList;
    }
}
