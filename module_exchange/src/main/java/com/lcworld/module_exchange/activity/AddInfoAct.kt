package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityAddInfoBinding
import com.lcworld.module_exchange.viewmodel.AddInfoViewModel

class AddInfoAct : BaseActivityEnhance<ExchangeActivityAddInfoBinding, AddInfoViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_add_info
    override fun initViewModel(): AddInfoViewModel = ViewModelProviders.of(this).get(AddInfoViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_add_info)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}