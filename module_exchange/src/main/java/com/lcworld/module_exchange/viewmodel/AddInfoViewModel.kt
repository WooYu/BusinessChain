package com.lcworld.module_exchange.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.activity.AddBankcardAct
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class AddInfoViewModel(application: Application) : BaseViewModelEnhance(application) {
    val name = ObservableField("")
    val idcard = ObservableField("")
    val phone = ObservableField("")
    val nextOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(AddBankcardAct::class.java)
    })

}