package com.lcworld.module_exchange.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.lcworld.library_base.base.BaseActivityEnhance
import com.lcworld.library_base.router.RouterActivityPath
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.databinding.ExchangeActivityWalletBinding
import com.lcworld.module_exchange.viewmodel.WalletViewModel

/**
 * 钱包
 */
@Route(path = RouterActivityPath.Exchange.PAGER_WALLET)
class WalletAct : BaseActivityEnhance<ExchangeActivityWalletBinding, WalletViewModel>() {
    override fun initVariableId(): Int = com.lcworld.module_exchange.BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_wallet

    override fun initViewModel(): WalletViewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)

    override fun initData() {
        super.initData()
        binding.layoutTitle.tvTitle.text = getString(R.string.exchange_wallet)
        binding.layoutTitle.ivBack.setOnClickListener { finish() }
    }
}