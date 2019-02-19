package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.lcworld.library_base.base.BaseViewModelEnhance
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class SetPwdViewModel(application: Application) : BaseViewModelEnhance(application) {
    val pwd = ObservableField("")
    val pwdConfirm = ObservableField("")
    val nextOnClickCommand = BindingCommand<Any>(BindingAction { })

}