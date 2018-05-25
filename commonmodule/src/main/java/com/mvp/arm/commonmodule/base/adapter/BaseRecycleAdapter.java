package com.mvp.arm.commonmodule.base.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * RecycleView 适配器
 *
 * @author Gjm
 * @date 2018/5/22
 */
public abstract class BaseRecycleAdapter<E> extends RecyclerView.Adapter<BaseRecycleHolder> implements IBaseAdapter<E>{
    protected List<E> mList = new ArrayList<>();
    protected Context mCtx;
    protected LayoutInflater mLayoutInflater;
    private int mLayoutId;

    /**
     * 点击事件
     */
    private OnItemClickListener<E> mOnItemClickListener;

    public BaseRecycleAdapter(Context context,List<E> list,int layoutId){
        this.mCtx = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        if(list != null){
            mList.addAll(list);
        }
    }

    @NonNull
    @Override
    public BaseRecycleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BaseRecycleHolder(mLayoutId == 0 ? getConvertView() : mLayoutInflater.inflate(mLayoutId,parent,false));
    }

    @Override
    public View getConvertView() {
        return new View(mCtx);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecycleHolder holder, int position) {
        E e = mList.get(position);

        holder.itemView.setOnClickListener(v -> {
            if(mOnItemClickListener != null){
                mOnItemClickListener.onItemClick(v,position,e);
            }
        });
        holder.itemView.setOnLongClickListener(v -> mOnItemClickListener != null && mOnItemClickListener.onItemLongClick(v,position,e));

        onBindView(holder,position,e);
    }

    @Override
    public int getItemCount() {
        return mList.size();
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

    public void setOnItemClickListener(OnItemClickListener<E> listener){
        mOnItemClickListener = listener;
    }
}
