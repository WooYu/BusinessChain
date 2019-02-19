package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.activity.VerifyPhoneNumberAct
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class AddBankcardViewModel(application: Application) : BaseViewModelEnhance(application) {
    val cardNumber = ObservableField("")
    val cardSelectOnClickCommand = BindingCommand<Any>(BindingAction { })
    val nextOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(VerifyPhoneNumberAct::class.java)
    })

}