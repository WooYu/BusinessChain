package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityAddBankcardBinding
import com.lcworld.module_exchange.viewmodel.AddBankcardViewModel

class AddBankcardAct : BaseActivityEnhance<ExchangeActivityAddBankcardBinding, AddBankcardViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_add_bankcard
    override fun initViewModel(): AddBankcardViewModel =
        ViewModelProviders.of(this).get(AddBankcardViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_add_bankcard)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}