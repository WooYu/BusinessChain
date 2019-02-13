package com.lcworld.module_home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;

/**
 * 首页顶部渐变背景
 */
public class HomeSubAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private VirtualLayoutManager.LayoutParams mLayoutParams;
    private int mLayoutId;
    private int mCount;

    public HomeSubAdapter(Context mContext, LayoutHelper mLayoutHelper, int mLayoutId, int mCount) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mLayoutId = mLayoutId;
        this.mCount = mCount;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new SubViewHolder(LayoutInflater.from(mContext).inflate(mLayoutId, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int viewType) {

    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class SubViewHolder extends RecyclerView.ViewHolder {

        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
