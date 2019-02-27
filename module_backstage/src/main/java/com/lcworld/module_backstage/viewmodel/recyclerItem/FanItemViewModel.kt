package com.lcworld.module_backstage.viewmodel.recyclerItem

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.base.ItemViewModel
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand


class FanItemViewModel<VM : BaseViewModel>(viewModel: VM) : ItemViewModel<VM>(viewModel) {
    val isDirect = ObservableBoolean(false)
    val date = ObservableField("2019-01-12 12:01")
    val name = ObservableField("张璇")
    val phone = ObservableField("手机号：13515451234")
    val itemOnClickCommand = BindingCommand<Any>(BindingAction {
    })


}