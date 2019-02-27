package com.lcworld.module_home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.blankj.utilcode.util.ObjectUtils;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataGoodsInfo;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

/**
 * 会员专区商品
 */
public class MemberAreaGoodsAdapter extends DelegateAdapter.Adapter<MemberAreaGoodsAdapter.MemberGoodsViewHolder> {
    private Context mContext;
    private LayoutHelper mLayoutHelper;
    private ItemClickListener mClickEvent;
    private List<DataGoodsInfo> mDatas;


    public MemberAreaGoodsAdapter(Context mContext, LayoutHelper mLayoutHelper, ItemClickListener mClickEvent) {
        this.mContext = mContext;
        this.mLayoutHelper = mLayoutHelper;
        this.mClickEvent = mClickEvent;
    }

    public List<DataGoodsInfo> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<DataGoodsInfo> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return mLayoutHelper;
    }

    @NonNull
    @Override
    public MemberGoodsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MemberGoodsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item_member_goods, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MemberGoodsViewHolder viewHolder, int viewType) {

    }

    @Override
    protected void onBindViewHolderWithOffset(MemberGoodsViewHolder holder, final int position, int offsetTotal, List<Object> payloads) {
        super.onBindViewHolderWithOffset(holder, position, offsetTotal, payloads);
        DataGoodsInfo bean = mDatas.get(position);
        GlideApp.with(mContext)
                .load(bean.getThumbnail())
                .error(R.mipmap.def_circle_a)
                .into(holder.ivPhoto);
        holder.tvName.setText(bean.getGoods_name());
        holder.tvOriginalprice.setText(ConvertExUtils.formatMoney(bean.getSprice()));
        holder.tvDicountprice.setText(ConvertExUtils.formatMoney(bean.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null == mClickEvent) {
                    return;
                }
                mClickEvent.clickedItem(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ObjectUtils.isEmpty(mDatas) ? 0 : mDatas.size();
    }

    class MemberGoodsViewHolder extends RecyclerView.ViewHolder {
        private QMUIRadiusImageView ivPhoto;
        private TextView tvName;
        private TextView tvDicountprice;
        private TextView tvOriginalprice;

        public MemberGoodsViewHolder(@NonNull View itemView) {
            super(itemView);

            ivPhoto = itemView.findViewById(R.id.iv_photo);
            tvName = itemView.findViewById(R.id.tv_name);
            tvDicountprice = itemView.findViewById(R.id.tv_dicountprice);
            tvOriginalprice = itemView.findViewById(R.id.tv_originalprice);
        }
    }

    public interface ItemClickListener {
        void clickedItem(int position);
    }

}
