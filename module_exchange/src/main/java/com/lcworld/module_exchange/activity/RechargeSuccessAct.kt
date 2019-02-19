package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityRechangeSuccessBinding
import com.lcworld.module_exchange.viewmodel.RechargeSuccessViewModel

class RechargeSuccessAct : BaseActivityEnhance<ExchangeActivityRechangeSuccessBinding, RechargeSuccessViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_rechange_success
    override fun initViewModel(): RechargeSuccessViewModel =
        ViewModelProviders.of(this).get(RechargeSuccessViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_recharge_success)
        binding.layoutTitle.tvRight.text = getString(R.string.exchange_finish)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.layoutTitle.tvRight.setOnClickListener { finish() }
    }
}