package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityVerifyPhoneNumberBinding
import com.lcworld.module_exchange.viewmodel.VerifyPhoneNumberViewModel

class VerifyPhoneNumberAct :
    BaseActivityEnhance<ExchangeActivityVerifyPhoneNumberBinding, VerifyPhoneNumberViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_verify_phone_number
    override fun initViewModel(): VerifyPhoneNumberViewModel =
        ViewModelProviders.of(this).get(VerifyPhoneNumberViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_verify_phone_number)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}