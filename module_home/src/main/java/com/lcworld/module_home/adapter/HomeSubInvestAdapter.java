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
 * 首页招商加盟
 */
public class HomeSubInvestAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private ItemClickListener mClickEvent;
    private int mCount;

    public HomeSubInvestAdapter(Context mContext, LayoutHelper mLayoutHelper, int mCount, ItemClickListener mClickEvent) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mCount = mCount;
        this.mClickEvent = mClickEvent;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new InvestViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_sub_invest, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int viewType) {

    }

    @Override
    protected void onBindViewHolderWithOffset(RecyclerView.ViewHolder holder, int position, final int offsetTotal) {
        super.onBindViewHolderWithOffset(holder, position, offsetTotal);
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
        return mCount;
    }

    class InvestViewHolder extends RecyclerView.ViewHolder {

        public InvestViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface ItemClickListener {
        void clickedItem(int position);
    }

}
