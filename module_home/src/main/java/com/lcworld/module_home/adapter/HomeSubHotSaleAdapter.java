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
 * 首页爆品抢购
 */
public class HomeSubHotSaleAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private ItemClickListener mClickEvent;

    public HomeSubHotSaleAdapter(Context mContext, LayoutHelper mLayoutHelper, ItemClickListener mClickEvent) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mClickEvent = mClickEvent;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new HotSaleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_sub_hotsale, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int viewType) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class HotSaleViewHolder extends RecyclerView.ViewHolder {

        public HotSaleViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null == mClickEvent) {
                        return;
                    }
                    mClickEvent.clickedItem(getLayoutPosition());
                }
            });
        }
    }

    public interface ItemClickListener {
        void clickedItem(int position);
    }

}
