package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.lcworld.library_base.base.BaseViewModelEnhance

class WithdrawSuccessViewModel(application: Application) : BaseViewModelEnhance(application) {
    val payType = ObservableField("招商银行储蓄卡（0145）")
    val amount = ObservableField("1000")

}