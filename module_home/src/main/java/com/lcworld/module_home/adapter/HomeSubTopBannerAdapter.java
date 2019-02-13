package com.lcworld.module_home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.bingoogolapple.bgabanner.BGABanner;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataFocusPictures;

import java.util.List;

/**
 * 首页顶部轮播图
 */
public class HomeSubTopBannerAdapter extends DelegateAdapter.Adapter<HomeSubTopBannerAdapter.TopBannerViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private List<DataFocusPictures> mDatas;
    private int mCount = 1;

    public HomeSubTopBannerAdapter(Context mContext, LayoutHelper mLayoutHelper, int mCount) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mCount = mCount;
    }

    public HomeSubTopBannerAdapter(Context mContext, LayoutHelper mLayoutHelper, List<DataFocusPictures> mDatas) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mDatas = mDatas;
    }

    public void setmDatas(List<DataFocusPictures> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public TopBannerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new TopBannerViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_sub_topbanner, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final TopBannerViewHolder viewHolder, int viewType) {
        if (ObjectUtils.isEmpty(mDatas)) {
            return;
        }

        viewHolder.bgaBanner.setAdapter(new BGABanner.Adapter<ImageView, DataFocusPictures>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, @Nullable DataFocusPictures model, int position) {
                GlideApp.with(mContext)
                        .load(model.getPic_url())
                        .error(R.mipmap.def_circle_a)
                        .placeholder(R.mipmap.def_circle_a)
                        .into(itemView);
            }

        });

        viewHolder.bgaBanner.setAutoPlayAble(mDatas.size() > 1);
        viewHolder.bgaBanner.setData(mDatas, null);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    class TopBannerViewHolder extends RecyclerView.ViewHolder {
        BGABanner bgaBanner;

        TopBannerViewHolder(@NonNull View itemView) {
            super(itemView);
            bgaBanner = itemView.findViewById(R.id.top_banner);
        }
    }

}
