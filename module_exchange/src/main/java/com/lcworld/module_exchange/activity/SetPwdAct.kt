package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivitySetPwdBinding
import com.lcworld.module_exchange.viewmodel.SetPwdViewModel

class SetPwdAct : BaseActivityEnhance<ExchangeActivitySetPwdBinding, SetPwdViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_set_pwd
    override fun initViewModel(): SetPwdViewModel = ViewModelProviders.of(this).get(SetPwdViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_set_pwd)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}