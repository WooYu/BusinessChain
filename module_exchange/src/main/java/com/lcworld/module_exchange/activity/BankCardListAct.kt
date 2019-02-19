package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityBankcardListBinding
import com.lcworld.module_exchange.viewmodel.BankCardListViewModel

class BankCardListAct : BaseActivityEnhance<ExchangeActivityBankcardListBinding, BankCardListViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_bankcard_list
    override fun initViewModel(): BankCardListViewModel =
        ViewModelProviders.of(this).get(BankCardListViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_choose_bankcard)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}