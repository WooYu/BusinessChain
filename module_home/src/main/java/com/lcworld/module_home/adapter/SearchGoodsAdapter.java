package com.lcworld.module_home.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataGoodsInfo;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

public class SearchGoodsAdapter extends BaseQuickAdapter<DataGoodsInfo, BaseViewHolder> {
    private boolean mCheckLinear = true;
    private final int Type_Linear = 0x00001111;
    private final int Type_Grid = 0x00002222;

    public void switchDisplayMode(int layoutResId) {
        mLayoutResId = layoutResId;
        notifyItemRangeChanged(0, mData.size());
    }

//    @Override
//    public int getItemViewType(int position) {
//        super.getItemViewType(position);
//        return mCheckLinear ? Type_Linear : Type_Grid;
//    }

    public SearchGoodsAdapter(int layoutResId, @Nullable List<DataGoodsInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataGoodsInfo item) {
        GlideApp.with(mContext)
                .load(item.getThumbnail())
                .into((QMUIRadiusImageView) helper.getView(R.id.iv_photo));
        helper.setText(R.id.tv_name, item.getGoods_name());
        helper.setText(R.id.tv_originalprice, String.format(mContext.getString(R.string.format_money)
                , ConvertExUtils.formatMoney(item.getSprice())));
        helper.setText(R.id.tv_dicountprice, String.format(mContext.getString(R.string.format_money)
                , ConvertExUtils.formatMoney(item.getPrice())));

    }
}
