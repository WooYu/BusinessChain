package com.lcworld.module_backstage.viewmodel.recyclerItem

import android.databinding.ObservableField
import me.goldze.mvvmhabit.base.BaseViewModel
import me.goldze.mvvmhabit.base.ItemViewModel
import me.goldze.mvvmhabit.binding.command.BindingAction
import me.goldze.mvvmhabit.binding.command.BindingCommand


class FanBillItemViewModel<VM : BaseViewModel>(viewModel: VM) : ItemViewModel<VM>(viewModel) {
    val date = ObservableField("2019-01-12 12:01")
    val consumerSource = ObservableField("美团外卖")
    val consumerType = ObservableField("日用")
    val consumerAmount = ObservableField("-15.1")
    val itemOnClickCommand = BindingCommand<Any>(BindingAction {

    })


}