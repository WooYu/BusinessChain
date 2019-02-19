package com.lcworld.module_exchange.viewmodel

import android.app.Application
import com.lcworld.library_base.base.BaseViewModelEnhance
import com.lcworld.module_exchange.activity.AddInfoAct
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class NoBankcardViewModel(application: Application) : BaseViewModelEnhance(application) {
    val nextOnClickCommand = BindingCommand<Any>(BindingAction {
        startActivity(AddInfoAct::class.java)
    })

}