package com.lcworld.module_order.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataCartVo;

import java.util.List;

/**
 * 确认订单A的商品Adapter
 */
public class OrderConfirmGoodsAdapter extends BaseQuickAdapter<DataCartVo, BaseViewHolder> {


    public OrderConfirmGoodsAdapter(int layoutResId, @Nullable List<DataCartVo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataCartVo item) {
        GlideApp.with(mContext)
                .load("http://javashop-statics.oss-cn-beijing.aliyuncs.com/demo/DB1591F429B64868B0633C329711F932.jpg")
                .into((ImageView) helper.getView(R.id.iv_photo));
        helper.setText(R.id.tv_name, item.getSeller_name());
        helper.setText(R.id.tv_price, String.format(mContext.getString(R.string.format_money)
                , ConvertExUtils.formatMoney(item.getPrice().getTotal_price())));
        helper.setText(R.id.tv_operate_num,String.valueOf(item.getWeight()));
    }
}
