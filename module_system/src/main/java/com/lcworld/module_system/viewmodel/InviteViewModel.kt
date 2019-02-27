package com.lcworld.module_system.viewmodel

import android.app.Application
import android.databinding.ObservableField
import com.lcworld.library_base.base.BaseViewModelEnhance
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class InviteViewModel(application: Application) : BaseViewModelEnhance(application) {

    val inviteCode = ObservableField("")
    val inviteOnClickCommand = BindingCommand<Any>(BindingAction {
    })
    val copyOnClickCommand = BindingCommand<Any>(BindingAction {
    })


}