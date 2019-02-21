package com.lcworld.module_order.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataSKUVo;

import java.util.List;

/**
 * 确认订单A的商品Adapter
 */
public class OrderConfirmGoodsAdapter extends BaseQuickAdapter<DataSKUVo, BaseViewHolder> {


    public OrderConfirmGoodsAdapter(int layoutResId, @Nullable List<DataSKUVo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataSKUVo item) {
        GlideApp.with(mContext)
                .load(item.getGoods_image())
                .into((ImageView) helper.getView(R.id.iv_photo));
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_price, String.format(mContext.getString(R.string.format_money)
                , ConvertExUtils.formatMoney(item.getOriginal_price())));
        helper.setText(R.id.tv_operate_num, String.format(
                mContext.getString(R.string.order_format_purchasequantity), String.valueOf(item.getNum())));
    }
}
