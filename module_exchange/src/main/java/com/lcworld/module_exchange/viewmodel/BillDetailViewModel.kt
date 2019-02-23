package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.R
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class BillDetailViewModel(application: Application) : BaseViewModelEnhance(application) {
    val payTypeTitle = ObservableField("微信支付")
    val payTypeImage = ObservableInt(R.mipmap.exchange_weixin)
    val amount = ObservableField("")
    val amountType = ObservableBoolean(false)
    val account = ObservableField("中商北斗")
    val billId = ObservableField("20190214039554545")
    val time = ObservableField("2019-02-14")

}