package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityBillDetailBinding
import com.lcworld.module_exchange.model.BillListItem
import com.lcworld.module_exchange.viewmodel.BillDetailViewModel

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
        val item  = intent.getParcelableExtra<BillListItem>(ITEM)?:finish()
    }
}