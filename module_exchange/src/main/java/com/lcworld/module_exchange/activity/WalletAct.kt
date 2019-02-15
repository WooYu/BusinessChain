package com.lcworld.module_exchange.activity

import android.os.Bundle
import com.lcworld.module_exchange.R
import com.lcworld.module_exchange.viewmodel.WalletViewModel
import me.goldze.mvvmhabit.base.BaseActivity

class WalletAct:BaseActivity<com.lcworld.module_exchange.databinding.ExchangeActivityWalletBinding,WalletViewModel>() {
    override fun initVariableId(): Int = com.lcworld.module_exchange.BR.viewModel

    override fun initContentView(p0: Bundle?): Int = R.layout.exchange_activity_wallet
}