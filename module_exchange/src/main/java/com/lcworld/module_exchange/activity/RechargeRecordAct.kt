package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityRechangeRecordBinding
import com.lcworld.module_exchange.viewmodel.RechargeRecordViewModel

class RechargeRecordAct : BaseActivityEnhance<ExchangeActivityRechangeRecordBinding, RechargeRecordViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_rechange_record
    override fun initViewModel(): RechargeRecordViewModel =
        ViewModelProviders.of(this).get(RechargeRecordViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_recharge_record)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}