package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityBillListBinding
import com.lcworld.module_exchange.viewmodel.BillListViewModel

class BillListAct : BaseActivityEnhance<ExchangeActivityBillListBinding, BillListViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_bill_list
    override fun initViewModel(): BillListViewModel =
        ViewModelProviders.of(this).get(BillListViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_bill)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}