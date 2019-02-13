package com.lcworld.module_home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataSpellDeals;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * 首页拼团采集
 */
public class HomeSubSpellPurchaseAdapter extends DelegateAdapter.Adapter<HomeSubSpellPurchaseAdapter.SpellBuyViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private ItemClickListener mClickEvent;
    private List<DataSpellDeals> mDatas;

    public HomeSubSpellPurchaseAdapter(Context mContext, LayoutHelper mLayoutHelper, ItemClickListener mClickEvent, List<DataSpellDeals> mDatas) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mClickEvent = mClickEvent;
        this.mDatas = mDatas;
    }

    public void setmDatas(List<DataSpellDeals> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public HomeSubSpellPurchaseAdapter.SpellBuyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new SpellBuyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_sub_spellpurchase, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeSubSpellPurchaseAdapter.SpellBuyViewHolder viewHolder, int viewType) {
    }

    @Override
    protected void onBindViewHolderWithOffset(HomeSubSpellPurchaseAdapter.SpellBuyViewHolder holder, int position, final int offsetTotal) {
        super.onBindViewHolderWithOffset(holder, position, offsetTotal);
        GlideApp.with(mContext)
                .load(mDatas.get(position).getImg_url())
                .error(R.mipmap.def_circle_a)
                .into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == mClickEvent) {
                    return;
                }
                mClickEvent.clickedItem(offsetTotal);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ObjectUtils.isEmpty(mDatas) ? 0 : mDatas.size();
    }

    class SpellBuyViewHolder extends RecyclerView.ViewHolder {
        QMUIRadiusImageView imageView;

        public SpellBuyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_photo);
        }
    }

    public interface ItemClickListener {
        void clickedItem(int position);
    }

}
