package com.lcworld.module_home.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.extension.utils.ConvertExUtils;
import com.lcworld.module_home.R;
import com.lcworld.module_home.bean.DataGoodsInfo;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

public class SearchGoodsAdapter extends BaseQuickAdapter<DataGoodsInfo, BaseViewHolder> {

    public void switchDisplayMode(int layoutResId) {
        mLayoutResId = layoutResId;
    }

    public SearchGoodsAdapter(int layoutResId, @Nullable List<DataGoodsInfo> data) {
        super(layoutResId, data);
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = super.getItemViewType(position);
        if (viewType == 0) {
            return mLayoutResId;
        }
        return viewType;
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
//        ((TextView)helper.getView(R.id.tv_originalprice)).setCompoundDrawablesWithIntrinsicBounds(null,
//               mContext.getResources().getDrawable(R.mipmap.home_filtrate_grid, null), null, null);
//        ((TextView)helper.getView(R.id.tv_dicountprice)).setCompoundDrawablesWithIntrinsicBounds(null,
//                mContext.getResources().getDrawable(R.mipmap.home_filtrate_grid, null), null, null);
    }

}
