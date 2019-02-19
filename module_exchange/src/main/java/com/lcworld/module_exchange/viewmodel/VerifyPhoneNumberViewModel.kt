package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.activity.SetPwdAct
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class VerifyPhoneNumberViewModel(application: Application) : BaseViewModelEnhance(application) {
    val number = ObservableField("")
    val verifyCode = ObservableField("")
    val codeBtnText = ObservableField("验证码")
    val nextOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(SetPwdAct::class.java)
    })
    val getCodeOnClickCommand = BindingCommand<Any>(BindingAction { })

}