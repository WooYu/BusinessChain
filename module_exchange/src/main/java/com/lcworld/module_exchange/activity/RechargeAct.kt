package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.module_exchange.BR
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityRechargeBinding
import com.lcworld.module_exchange.viewmodel.RechargeViewModel
import org.jetbrains.anko.startActivity

class RechargeAct : BaseActivityEnhance<ExchangeActivityRechargeBinding, RechargeViewModel>() {
    override fun initVariableId(): Int = BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_recharge
    override fun initViewModel(): RechargeViewModel = ViewModelProviders.of(this).get(RechargeViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_recharge)
        binding.layoutTitle.tvRight.text = getString(R.string.exchange_recharge_record)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
        binding.layoutTitle.tvRight.setOnClickListener { startActivity<RechargeRecordAct>() }
    }
}