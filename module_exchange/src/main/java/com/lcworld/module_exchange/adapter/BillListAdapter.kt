package com.lcworld.module_exchange.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.TimeUtil
import com.lcworld.module_exchange.model.BillListItem
import java.util.*

class BillListAdapter(layoutResId: Int, data: List<BillListItem>) :
    BaseQuickAdapter<BillListItem, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: BillListItem) {
        helper.setText(R.id.tv_type,item.order_name)
        helper.setText(R.id.tv_amount,item.pay_money)
        helper.setTextColor(R.id.tv_amount,getTextColor(item.type))
        helper.setImageResource(R.id.iv_pay_type,getImage(item.pay_type))
        helper.setText(R.id.tv_time,TimeUtil.formatDataType1(Date(item.create_time.toLong().times(1000))))
    }

    private fun getTextColor(type: String): Int = if (type == "2") R.color.dominant_hue else R.color.tx_colorb
    private fun getImage(type: String): Int = if (type == "1") R.mipmap.exchange_weixin else R.mipmap.exchange_alipay
}
