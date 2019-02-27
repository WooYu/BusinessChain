package com.lcworld.module_system.viewmodel

import android.app.Application
import com.lcworld.library_base.base.BaseViewModelEnhance
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class ToolEntranceViewModel(application: Application) : BaseViewModelEnhance(application) {

    val shareOnClickCommand = BindingCommand<Any>(BindingAction {
    })
    val questionOnClickCommand = BindingCommand<Any>(BindingAction {
    })
    val feedbackOnClickCommand = BindingCommand<Any>(BindingAction {
    })
    val aboutUsOnClickCommand = BindingCommand<Any>(BindingAction {
    })

}