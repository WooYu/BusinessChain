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
    val payTypeTitle = ObservableField("")
    val payTypeImage = ObservableInt()
    val amount = ObservableField("")
    val amountType = ObservableBoolean(false)
    val account = ObservableField("")
    val billId = ObservableField("")
    val time = ObservableField("")

}