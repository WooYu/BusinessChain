package com.lcworld.module_order.adapter;

import android.support.annotation.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcworld.businesschain.GlideApp;
import com.lcworld.library_base.extension.ConvertExUtils;
import com.lcworld.module_order.R;
import com.lcworld.module_order.bean.DataOrderDTO;
import com.lcworld.module_order.bean.DataSKUVo;
import com.qmuiteam.qmui.widget.QMUIRadiusImageView;

import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<DataOrderDTO, BaseViewHolder> {
    public OrderListAdapter(int layoutResId, @Nullable List<DataOrderDTO> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataOrderDTO item) {
        //订单编号
        helper.setText(R.id.tv_ordernum, String.format(mContext.getString(R.string.order_format_ordernum), item.getSn()));
        //订单状态
        helper.setText(R.id.tv_orderstatus, item.getOrder_status_text());
        //图片
        DataSKUVo dataSKUVo = item.getSku_list().get(0);
        GlideApp.with(mContext)
                .load(dataSKUVo.getGoods_image())
                .into((QMUIRadiusImageView) helper.getView(R.id.iv_photo));
        //商品名称
        helper.setText(R.id.tv_name, dataSKUVo.getName());
        //商品价格
        helper.setText(R.id.tv_price, String.format(mContext.getString(R.string.format_money)
                , ConvertExUtils.formatMoney(dataSKUVo.getPurchase_price())));
        //购买数量
        helper.setText(R.id.tv_quantity, String.format(mContext.getString(R.string.order_format_purchasequantity_int)
                , dataSKUVo.getNum()));
        //底部按钮
        String order_status = item.getOrder_status();
        //ALL:所有订单,WAIT_PAY:待付款,WAIT_SHIP:待发货,WAIT_ROG:待收货," +"CANCELLED:已取消,COMPLETE:已完成,WAIT_COMMENT:待评论,REFUND:售后中"
        String[] statusArr = mContext.getResources().getStringArray(R.array.order_status_display);

        helper.setVisible(R.id.group_paid, false);
        helper.setVisible(R.id.group_settled, false);
        helper.setVisible(R.id.group_invalid, false);
        if (order_status.equals(statusArr[3]) || order_status.equals(statusArr[4]) || order_status.equals(statusArr[5])) {
            helper.setVisible(R.id.group_paid, true);
        } else if (order_status.equals(statusArr[6])) {
            helper.setVisible(R.id.group_settled, true);
        } else if (order_status.equals(statusArr[7])) {
            helper.setVisible(R.id.group_invalid, true);
        }

        //点击事件btn_paid_pickup,btn_paid_buyagain /btn_settled_buyagain   /btn_invalid_buyagain
        helper.addOnClickListener(R.id.btn_paid_pickup)
                .addOnClickListener(R.id.btn_paid_buyagain)
                .addOnClickListener(R.id.btn_settled_buyagain)
                .addOnClickListener(R.id.btn_invalid_buyagain);
    }
}
