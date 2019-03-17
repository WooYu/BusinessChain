package com.lcworld.module_order.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.extension.utils.ConvertExUtils;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataSKUVo;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

public class TrolleyAdapter extends BaseQuickAdapter<DataSKUVo, BaseViewHolder> {
    public TrolleyAdapter(int layoutResId, @Nullable List<DataSKUVo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataSKUVo item) {
        int[] checkStatus = mContext.getResources().getIntArray(R.array.config_trolley_checkstatus);
        boolean bChecked = checkStatus[1] == item.getChecked();
        helper.setImageResource(R.id.iv_pick, (bChecked ? R.mipmap.cboxa_select : R.mipmap.cboxa_unselect));
        GlideApp.with(mContext)
                .load(item.getGoods_image())
                .into((QMUIRadiusImageView) helper.getView(R.id.iv_photo));
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_price, String.format(mContext.getString(R.string.format_money)
                , ConvertExUtils.formatMoney(item.getPurchase_price())));
        helper.setText(R.id.tv_quantity_value, String.valueOf(item.getNum()));
        helper.addOnClickListener(R.id.tv_quantity_value)
                .addOnClickListener(R.id.bg_add)
                .addOnClickListener(R.id.bg_sub)
                .addOnClickListener(R.id.iv_pick);
    }
}
