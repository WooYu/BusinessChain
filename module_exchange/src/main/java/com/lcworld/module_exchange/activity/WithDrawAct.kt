package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityWithdrawBinding
import com.lcworld.module_exchange.viewmodel.WithdrawViewModel

class WithDrawAct : BaseActivityEnhance<ExchangeActivityWithdrawBinding, WithdrawViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_withdraw
    override fun initViewModel(): WithdrawViewModel = ViewModelProviders.of(this).get(WithdrawViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_withdraw)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}