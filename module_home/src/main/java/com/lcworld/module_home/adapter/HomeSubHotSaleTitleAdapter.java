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
 * 首页自由购标题
 */
public class HomeSubHotSaleTitleAdapter extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private ItemClickListener mClickEvent;

    public HomeSubHotSaleTitleAdapter(Context mContext, LayoutHelper mLayoutHelper, ItemClickListener mClickEvent) {
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
        return new TitleViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_sub_hotsale_title, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int viewType) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        TitleViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.findViewById(R.id.tv_right).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null == mClickEvent) {
                        return;
                    }
                    mClickEvent.clickedMore();
                }
            });
        }
    }

    public interface ItemClickListener {
        void clickedMore();
    }

}
