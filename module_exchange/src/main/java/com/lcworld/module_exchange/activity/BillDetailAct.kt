package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.TimeUtil
import com.lcworld.module_exchange.databinding.ExchangeActivityBillDetailBinding
import com.lcworld.module_exchange.model.BillListItem
import com.lcworld.module_exchange.viewmodel.BillDetailViewModel
import java.util.*

class BillDetailAct : BaseActivityEnhance<ExchangeActivityBillDetailBinding, BillDetailViewModel>() {

    companion object {
        const val ITEM = "ITEM"
    }

    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_bill_detail
    override fun initViewModel(): BillDetailViewModel =
        ViewModelProviders.of(this).get(BillDetailViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_bill_detail)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        val item = intent.getParcelableExtra<BillListItem>(ITEM) ?: return
        //1增 2减
        val payMoney = TimeUtil.formatAmount(item.pay_money)
        val amountStr = if (item.pay_type == 1) "+$payMoney" else "-$payMoney"
        viewModel.amount.set(amountStr)
        viewModel.amountType.set(item.pay_type == 1)
        //11支付宝 22微信 2商币 3余额
        val image = when (item.type) {
            11 -> R.mipmap.exchange_pay_type_alipay
            22 -> R.mipmap.exchange_weixin
            2 -> R.mipmap.exchange_pay_type_chain
            else -> R.mipmap.exchange_pay_type_balance
        }
        viewModel.payTypeImage.set(image)
        viewModel.payTypeTitle.set(item.pay_method)
        viewModel.time.set(TimeUtil.formatData(Date(item.create_time.times(1000)), "yyyy-MM-dd"))
        viewModel.account.set(item.member_id)
        viewModel.billId.set(item.order_sn)
    }
}