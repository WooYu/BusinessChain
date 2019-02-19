package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityNoBankcardBinding
import com.lcworld.module_exchange.viewmodel.NoBankcardViewModel

class NoBankcardAct : BaseActivityEnhance<ExchangeActivityNoBankcardBinding, NoBankcardViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_no_bankcard
    override fun initViewModel(): NoBankcardViewModel = ViewModelProviders.of(this).get(NoBankcardViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_bankcard)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}