package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityWithdrawSuccessBinding
import com.lcworld.module_exchange.viewmodel.WithdrawSuccessViewModel

class WithdrawSuccessAct : BaseActivityEnhance<ExchangeActivityWithdrawSuccessBinding, WithdrawSuccessViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_withdraw_success
    override fun initViewModel(): WithdrawSuccessViewModel =
        ViewModelProviders.of(this).get(WithdrawSuccessViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_waiting_account)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}