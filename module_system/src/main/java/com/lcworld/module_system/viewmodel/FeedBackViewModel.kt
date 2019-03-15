package com.lcworld.module_system.viewmodel

import android.app.Application
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.databinding.ObservableInt
import com.lcworld.library_base.base.BaseViewModelEnhance
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand

class FeedBackViewModel(application: Application) : BaseViewModelEnhance(application) {
    val photo1 = ObservableField("")
    val photo2 = ObservableField("")
    val photo3 = ObservableField("")
    val inputLengthTips = ObservableField("0/300")
    val showAdd1 = ObservableBoolean(true)//0 可见 8 不可见
    val showAdd2 = ObservableBoolean(true)
    val showAdd3 = ObservableBoolean(true)
    val btnEnable = ObservableBoolean(false)

}