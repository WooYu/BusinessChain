package com.lcworld.module_home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.lcworld.module_home.R;

/**
 * 首页顶部轮播图
 */
public class HomeSubTopBannerAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private int mCount;

    public void setData(){
        //TODO: 接受轮播图数据
        notifyDataSetChanged();
    }

    public HomeSubTopBannerAdapter(Context mContext, LayoutHelper mLayoutHelper, int mCount) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mCount = mCount;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new TopBannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_sub_topbanner, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int viewType) {

    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class TopBannerViewHolder extends RecyclerView.ViewHolder {

        public TopBannerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
